package com.github.whysopowerful;

import java.util.Random;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
// import net.minecraft.loot.ConstantLootTableRange;  // this doesn't exist anymore in 1.17+ [http://5.9.10.113/66460988/constantloottablerange-fabric-21w08b-is-missing]
// instead of ConstantLootTableRange, I use ConstantLootNumberProvider
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.registry.Registry;

public class LuckyLoot implements ModInitializer {
	
	Random random = new Random();
	Block randomBlock;
	Item randomItem;

	@Override
	public void onInitialize() {
		
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (Math.random() > .5F) {
				randomBlock = Registry.BLOCK.getRandom(random);
				
		    	FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
		    			.rolls(ConstantLootNumberProvider.create(1))
		    			.with(ItemEntry.builder(randomBlock));
		    	
		    	supplier.pool(poolBuilder);
		    	
//		    	System.out.println("id: " + id + "   --->   " + "Random block: " + randomBlock);
			} else {
				randomItem = Registry.ITEM.getRandom(random);
				
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
    			.rolls(ConstantLootNumberProvider.create(1))
    			.with(ItemEntry.builder(randomItem));
    	
				supplier.pool(poolBuilder);
				
//				System.out.println("id: " + id + "   --->   " + "Random item: " + randomItem);
			}
			}
		);
		
	}

}
