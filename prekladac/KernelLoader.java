package prekladac;
public class KernelLoader implements ILoader{
	@Override public final byte[] loader(){
		return new byte[]{
			(byte)0xea,(byte)0x05,(byte)0x00,(byte)0xc0,
			(byte)0x07,(byte)0x31,(byte)0xc0,(byte)0x8e,
			(byte)0xc0,(byte)0xe4,(byte)0x92,(byte)0x0c,
			(byte)0x02,(byte)0xe6,(byte)0x92,(byte)0x8c,
			(byte)0xc8,(byte)0x8e,(byte)0xd8,(byte)0xbd,
			(byte)0x00,(byte)0x90,(byte)0x89,(byte)0xec,
			(byte)0xbe,(byte)0x03,(byte)0x00,(byte)0x83,
			(byte)0xfe,(byte)0x00,(byte)0x74,(byte)0x37,
			(byte)0x4e,(byte)0xba,(byte)0x80,(byte)0x00,
			(byte)0x31,(byte)0xc0,(byte)0xcd,(byte)0x13,
			(byte)0xb4,(byte)0x02,(byte)0xb5,(byte)0x00,
			(byte)0xb8,(byte)0x00,(byte)0x00,(byte)0xbb,
			(byte)0xe0,(byte)0x07,(byte)0xe8,(byte)0x2b,
			(byte)0x00,(byte)0x72,(byte)0x20,(byte)0xfa,
			(byte)0x1e,(byte)0x0f,(byte)0x01,(byte)0x16,
			(byte)0xb9,(byte)0x01,(byte)0x0f,(byte)0x20,
			(byte)0xc0,(byte)0x0c,(byte)0x01,(byte)0x0f,
			(byte)0x22,(byte)0xc0,(byte)0xeb,(byte)0x00,
			(byte)0xbb,(byte)0x08,(byte)0x00,(byte)0x8e,
			(byte)0xdb,(byte)0x24,(byte)0xfe,(byte)0x0f,
			(byte)0x22,(byte)0xc0,(byte)0x1f,(byte)0xfb,
			(byte)0xe9,(byte)0x4c,(byte)0x01,(byte)0x31,
			(byte)0xc0,(byte)0xcd,(byte)0x16,(byte)0xea,
			(byte)0x00,(byte)0x00,(byte)0xff,(byte)0xff,
			(byte)0x88,(byte)0xe1,(byte)0xb4,(byte)0x02,
			(byte)0x8e,(byte)0xc3,(byte)0x31,(byte)0xdb,
			(byte)0xcd,(byte)0x13,(byte)0xc3,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x51,(byte)0x66,(byte)0x53,(byte)0xb9,
			(byte)0x20,(byte)0x00,(byte)0x66,(byte)0x31,
			(byte)0xdb,(byte)0x30,(byte)0xe4,(byte)0xcd,
			(byte)0x16,(byte)0xb4,(byte)0x0e,(byte)0xcd,
			(byte)0x10,(byte)0x2c,(byte)0x30,(byte)0xa8,
			(byte)0xfe,(byte)0x75,(byte)0x08,(byte)0x66,
			(byte)0xd1,(byte)0xe3,(byte)0x08,(byte)0xc3,
			(byte)0x49,(byte)0x75,(byte)0xea,(byte)0x66,
			(byte)0x89,(byte)0xd8,(byte)0x66,(byte)0x5b,
			(byte)0x59,(byte)0xcb,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x66,(byte)0x60,(byte)0x66,(byte)0x89,
			(byte)0xc3,(byte)0xb9,(byte)0x20,(byte)0x00,
			(byte)0xb4,(byte)0x0e,(byte)0xb0,(byte)0x31,
			(byte)0x66,(byte)0xd1,(byte)0xe3,(byte)0x72,
			(byte)0x02,(byte)0xb0,(byte)0x30,(byte)0xcd,
			(byte)0x10,(byte)0x49,(byte)0x75,(byte)0xf2,
			(byte)0xb0,(byte)0x0a,(byte)0xcd,(byte)0x10,
			(byte)0xb0,(byte)0x0d,(byte)0xcd,(byte)0x10,
			(byte)0x66,(byte)0x61,(byte)0xcb,(byte)0x66,
			(byte)0xb8,(byte)0xff,(byte)0xff,(byte)0xff,
			(byte)0x00,(byte)0x66,(byte)0x89,(byte)0xc5,
			(byte)0x66,(byte)0x89,(byte)0xc4,(byte)0x66,
			(byte)0x31,(byte)0xc0,(byte)0x8e,(byte)0xd8,
			(byte)0xea,(byte)0x00,(byte)0x00,(byte)0xe0,
			(byte)0x07,(byte)0x0f,(byte)0x00,(byte)0xbf,
			(byte)0x7d,(byte)0x00,(byte)0x00,(byte)0x00,
			(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
			(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xff,
			(byte)0xff,(byte)0x00,(byte)0x00,(byte)0x00,
			(byte)0x92,(byte)0xcf,(byte)0x00,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,
			(byte)0x90,(byte)0x90,(byte)0x55,(byte)0xaa
		};
	}
	@Override public final byte[] read(){
		return new byte[]{
			(byte)0x9a,(byte)0x00,(byte)0x01,(byte)0xc0,(byte)0x07
		};
	}
	@Override public final byte[] write(){
		return new byte[]{
			(byte)0x9a,(byte)0x80,(byte)0x01,(byte)0xc0,(byte)0x07
		};
	}
}
