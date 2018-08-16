<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/four.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../css/jqe/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../css/jqe/themes/icon.css" />

<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

<script type="text/javascript">
	
</script>

<%
	//判断权限 :session中取回用户，有权限继续，没权限导航去错误页
%>

</head>
<body>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><table width="96%" border="0"
					align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
					<tr align="left" bgcolor="">
						<td colspan="14" class="optiontitle">未进行入库计划的订单</td>

					</tr>


					<tr align="center">


						<td align="center" bgcolor="#ebf0f7">计费</td>
						<td align="center" bgcolor="#ebf0f7">订单编号</td>
						<td align="center" bgcolor="#ebf0f7">货物名</td>
						<td align="center" bgcolor="#ebf0f7">货物分类</td>

					</tr>
					<c:forEach items="${lsReg}" var="oneReg">
						<tr align="center" bgcolor="#FFFFFF">

							
							<td align="center">${oneReg.regist_paystate==1?'已付':'未付'}</td>
							<td align="center">${oneReg.regist_no}</td>
							<td align="center">${oneReg.good_name}</td>
							<td align="center">${oneReg.good_type}</td>


						</tr>
					</c:forEach>

				</table></td>
		</tr>
	
	</table>


</body>
</html>