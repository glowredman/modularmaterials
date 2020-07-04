package glowredman.modularmaterials.command;

import static glowredman.modularmaterials.Reference.*;
import static net.minecraft.util.text.TextFormatting.*;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.oredict.OreDictionary;

public class Command extends CommandBase {

	@Override
	public String getName() {
		return commandName;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		if(sender instanceof EntityPlayer) {
			return commandName + " <getBiome|getMaterialKey|getMeta|hand|help|here>";
		} else {
			return commandName + " <getBiome|getMaterialKey|getMeta|help>";
		}
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length == 0) {
			printHelpPage(sender);
		} else {
			World world = sender.getEntityWorld();
			BlockPos pos = sender.getPosition();
			Biome biome = world.getBiome(pos);
			switch (args[0]) {
			case "here":
				if(sender instanceof EntityPlayer) {
					//pos info
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					//biome info
					String biomeName = biome.getBiomeName();
					String biomeKey = biome.getRegistryName().toString();
					int biomeID = Biome.getIdForBiome(biome);
					//dimension info
					String dimName = world.provider.getDimensionType().getName();
					int dimID = world.provider.getDimension();
					
					send(sender, GOLD + "Information for " + AQUA + '[' + x + ", " + y + ", " + z + ']' + GOLD + ':');
					send(sender, BLUE + "Biome Name: " + WHITE + biomeName);
					send(sender, BLUE + "Biome NamespacedID: " + WHITE + biomeKey);
					send(sender, BLUE + "Biome ID: " + WHITE + biomeID);
					send(sender, BLUE + "Dimension Name " + WHITE + dimName);
					send(sender, BLUE + "Dimension ID: " + WHITE + dimID);
				} else {
					send(sender, RED + "Command must be executed by a player!");
				}
				break;
			case "getMeta":
				if(args.length == 2) {
					String key = args[1];
					if(materials.containsKey(key)) {
						send(sender, key + ": " + materials.get(key).meta);
					} else {
						send(sender, RED + "The material '" + key + "' does not exist!");
					}
				} else {
					send(sender, RED + "Usage: /mm getMeta <materialKey>");
				}
				break;
			case "getMaterialKey":
				if(args.length == 2) {
					String arg = args[1];
					try {
						short key = Short.parseShort(arg);
						if(idMapping.containsKey(key)) {
							send(sender, key + ": " + idMapping.get(key));
						} else {
							send(sender, RED + "The meta '" + arg + "' is not occupied!");
						}
					} catch (Exception e) {
						send(sender, RED + "'" + arg + "' is not a valid number!");
					}
				} else {
					send(sender, RED + "Usage: /mm getMaterialKey <meta>");
				}
				break;
			case "hand":
				if(sender instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) sender;
					ItemStack stack = player.inventory.getCurrentItem();
					if(!stack.getItem().getRegistryName().equals(Blocks.AIR.getRegistryName())) {
						String out = stack.getItem().getRegistryName().toString() + ":" + stack.getMetadata();
						send(sender, out);
						for(int id : OreDictionary.getOreIDs(stack)) {
							send(sender, "> " + OreDictionary.getOreName(id));
						}
						//copy to clipboard
						StringSelection selection = new StringSelection(out);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);;
					} else {
						send(sender, "Your hand is empty!");
					}
				}
				break;
			case "getBiome":
				if(args.length == 2) {
					String arg = args[1];
					try {
						int id = Integer.parseInt(arg);
						Biome b = Biome.getBiomeForId(id);
						if(b != null) {
							send(sender, arg + ": " + b.getRegistryName().toString());
						} else {
							send(sender, "There is no biome with the id '" + arg + "'!");
						}
					} catch (Exception e) {
						send(sender, RED + "'" + arg + "' is not a valid number!");
					}
				} else {
					send(sender, RED + "Usage: /mm getBiome <id>");
				}
				break;
			default:
				printHelpPage(sender);
				break;
			}
		}
	}
	
	public void printHelpPage(ICommandSender sender) {
		if(sender instanceof EntityPlayer) {
			help(sender, "getBiome <id>");
			help(sender, "getMaterialKey <meta>");
			help(sender, "getMeta <materialKey>");
			help(sender, "hand");
			help(sender, "help");
			help(sender, "here");
		} else {
			help(sender, "getBiome <id>");
			help(sender, "getMaterialKey <meta>");
			help(sender, "getMeta <materialKey>");
			help(sender, "help");
		}
	}
	
	private void help(ICommandSender sender, String command) {
		send(sender, BLUE + "/" + commandName + ' ' + command);
	}
	
	private void send(ICommandSender sender, String msg) {
		sender.sendMessage(new TextComponentString(msg));
	}

}
