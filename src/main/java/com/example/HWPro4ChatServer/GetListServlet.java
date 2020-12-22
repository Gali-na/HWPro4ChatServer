package com.example.HWPro4ChatServer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}
		MessageList msgList = MessageList.getInstance();

			String json = msgList.toJSON(from);
			//String json= null;
			if (json != null) {
				OutputStream os = resp.getOutputStream();
				byte[] buf = json.getBytes(StandardCharsets.UTF_8);
				os.write(buf);
			}
    }
}
