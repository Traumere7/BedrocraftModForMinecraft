package com.traumere.bedrocraft.block.entity;

import com.traumere.bedrocraft.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// 1. 继承 BlockEntity
// 2. 实现 NamedScreenHandlerFactory (为了打开UI界面)
// 3. 实现 ImplementedInventory (为了拥有物品存储能力)
public class CompressorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    // 声明 10 个格子的存储空间 (0-8 是 3x3 输入，9 是输出)
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

    // 进度数据
    protected int progress = 0;
    protected int maxProgress = 72; // 假设合成需要 72 tick (约 3.6 秒)

    // PropertyDelegate 是一个“同步阵列”，它负责把服务端的 progress 数字自动发给客户端的 UI 去画进度条
    protected final PropertyDelegate propertyDelegate;

    public CompressorBlockEntity(BlockPos pos, BlockState state) {
        // ModBlockEntities.COMPRESSOR_BLOCK_ENTITY 是你接下来需要注册的实体类型
        super(ModBlockEntities.COMPRESSOR_BLOCK_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CompressorBlockEntity.this.progress;
                    case 1 -> CompressorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CompressorBlockEntity.this.progress = value;
                    case 1 -> CompressorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2; // 我们同步了两个数据：progress 和 maxProgress
            }
        };
    }

    // --- ImplementedInventory 必须实现的方法 ---
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // --- UI 界面相关的方法 ---
    @Override
    public Text getDisplayName() {
        // 这是 UI 界面左上角的标题文本
        return Text.translatable("block.bedrocraft.compressor");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // 返回我们之后要写的 ScreenHandler。把当前实体 (this) 和同步阵列传过去
        // return new CompressorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
        return null; // 占位
    }

    // --- 数据持久化 (存档读写) ---
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        // 保存物品
        Inventories.writeNbt(nbt, inventory);
        // 保存进度
        nbt.putInt("compressor.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        // 读取物品
        Inventories.readNbt(nbt, inventory);
        // 读取进度
        progress = nbt.getInt("compressor.progress");
    }

    // --- 核心运转逻辑 (Tick 引擎) ---
    public static void tick(World world, BlockPos pos, BlockState state, CompressorBlockEntity entity) {
        if (world.isClient()) return; // 纯服务端逻辑

        if (hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, pos, state); // 标记状态改变，确保存档能存下进度

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            if (entity.progress > 0) {
                entity.progress = 0; // 配方不匹配或被打断，进度清零
                markDirty(world, pos, state);
            }
        }
    }

    // 占位逻辑：检查是否有有效配方
    private static boolean hasRecipe(CompressorBlockEntity entity) {
        // TODO: 这里之后会替换为我们真正的 Recipe 系统
        // 现在的临时测试逻辑：只要第 0 格（左上角）有一块泥土，并且输出格（第 9 格）是空的或还没满，就能工作
        boolean hasDirtInFirstSlot = entity.getStack(0).getItem() == Items.DIRT;
        boolean canOutput = entity.getStack(9).isEmpty() ||
                (entity.getStack(9).getItem() == Items.DIAMOND && entity.getStack(9).getCount() < 64);

        return hasDirtInFirstSlot && canOutput;
    }

    // 占位逻辑：执行合成（吞噬输入，产生输出）
    private static void craftItem(CompressorBlockEntity entity) {
        // TODO: 这里之后会替换为我们真正的 Recipe 系统
        entity.removeStack(0, 1); // 消耗掉第 0 格的 1 个泥土

        ItemStack outputSlot = entity.getStack(9);
        if (outputSlot.isEmpty()) {
            entity.setStack(9, new ItemStack(Items.DIAMOND, 1)); // 产出 1 个钻石
        } else {
            outputSlot.increment(1); // 如果已经有钻石了，数量 +1
        }

        entity.progress = 0; // 合成完毕，进度清零
    }
}
