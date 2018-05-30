package com.bootdo.socket;

import com.bootdo.common.utils.TeaUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 编码器将数据直接发出去(不做处理)
 * @author Shinobi
 *
 */
public class ByteArrayEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		byte[] dataBytes = (byte[])message;
		System.out.println("服务端主动给客户发送消息");
		IoBuffer buffer = IoBuffer.allocate(256);
		buffer.setAutoExpand(true);
		buffer.put(dataBytes);

		buffer.flip();
		out.write(buffer);
		out.flush();
		buffer.free();
	}

}
