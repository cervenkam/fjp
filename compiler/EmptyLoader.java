package compiler;
import java.util.List;
/**
 * Empty template for system calls and program headers
 * @author Martin Cervenka A17N0029P
 * @version 20190101
 */
public class EmptyLoader implements ILoader{
	/**
	 * Returns opcodes at the beginning of the program
	 * @return Static begining of the program
	 */
	@Override
	public final byte[] loader(){
		return new byte[]{

		};
	}
	/**
	 * Returns opcodes for read system call
	 * @return System call opcode array
	 */
	@Override
	public final byte[] read(){
		return new byte[]{

		};
	}
	/**
	 * Returns opcodes for write system call
	 * @return System call opcode array
	 */
	@Override
	public final byte[] write(){
		return new byte[]{

		};
	}
	/**
	 * Finishes program
	 * @param program Opcodes of the compiled program
	 */
	@Override public void finish(List<Byte> program){

	}
}
