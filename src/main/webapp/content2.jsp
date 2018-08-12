<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>没权限</title>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF">
				<table width="40%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">


					<tr align="center" bgcolor="#F2FDFF">
						<th align="center" colspan="2" class="optiontitle">公告</th>
					</tr>

					<tr align="center" bgcolor="#F2FDFF">
						<td width="20%" align="left">功能暂时没有开放哟！<br>
							 -----杨昊
						</td>
					</tr>
				</table>
				</form></td>
		</tr>
	</table>
</body>
</html>
