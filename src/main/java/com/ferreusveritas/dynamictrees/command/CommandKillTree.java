package com.ferreusveritas.dynamictrees.command;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class CommandKillTree extends SubCommand {

	public static final String KILLTREE = "killtree";

	@Override
	public String getName() {
		return KILLTREE;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {

		switch (args.length) {
			case 2:
			case 3:
			case 4:
				return CommandBase.getTabCompletionCoordinate(args, 1, targetPos);
		}

		return super.getTabCompletions(server, sender, args, targetPos);
	}
	
	@Override
	public void execute(World world, ICommandSender sender, String[] args) throws CommandException {

		if (args.length < 4) {
			throw new WrongUsageException("commands.dynamictrees.killtree.usage");
		}

		BlockPos pos = CommandBase.parseBlockPos(sender, args, 1, false);

		BlockPos rootPos = TreeHelper.findRootNode(world, pos);
		if (rootPos != BlockPos.ORIGIN) {
			TreeHelper.getRooty(world.getBlockState(rootPos)).destroyTree(world, rootPos);
		} else {
			throw new CommandException("No tree found");
		}
	}

}
