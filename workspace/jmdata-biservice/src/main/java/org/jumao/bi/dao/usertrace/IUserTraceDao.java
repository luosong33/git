package org.jumao.bi.dao.usertrace;

import java.io.IOException;
import java.util.List;

import org.jumao.bi.entites.usertrace.UserTraceInfo;

public interface IUserTraceDao {
	
	void addUserTraceInfo(List<UserTraceInfo> userTraces) throws IOException;

}
