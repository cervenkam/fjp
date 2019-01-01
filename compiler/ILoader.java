package compiler;
import java.util.List;
/**
 * Interface for system calls and program headers
 * @author Martin Cervenka A17N0029P
 * @version 20190101
 */
public interface ILoader{
	/**
	 * Returns opcodes at the beginning of the program
	 * @return Static begining of the program
	 */
	public byte[] loader();
	/**
	 * Returns opcodes for read system call
	 * @return System call opcode array
	 */
	public byte[] read();
	/**
	 * Returns opcodes for write system call
	 * @return System call opcode array
	 */
	public byte[] write();
	/**
	 * Finishes program
	 * @param program Opcodes of the compiled program
	 */
	public void finish(List<Byte> program);
}
