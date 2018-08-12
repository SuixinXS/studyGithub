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
$(function(){
	   
	 
	   
	   $("#selArea").change(function(){
		 
		   location.href="showCab.do?area_id="+$(this).val();
	   });
	   
});

</script>

<%
	//判断权限 :session中取回用户，有权限继续，没权限导航去错误页
%>
<style type="text/css"> 


</style> 
</head>
<body>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF">
				<table width="96%" border="0" align="center" cellpadding="4"
					cellspacing="1" bgcolor="#FFFFFF">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle" >卡位显示<select id="selArea">
						       <option value="0">全部</option>
						      <c:forEach items="${arealist}" var="oneAre">
						          <option ${aid==oneAre.area_id?'selected':''}  value="${oneAre.area_id}">${oneAre.area_name}</option>						      
						      </c:forEach> 
						   </select></td>
					</tr>


					<c:forEach items="${lsCab}" var="oneCab">
						<tr>
							<tr align="left" bgcolor="#F2FDFF">
								<td colspan="10" style="border:1px solid;border-color:#aec3de;">卡位名：${oneCab.cab_no }  &nbsp;&nbsp;&nbsp; 卡位id：${oneCab.cab_id } </td>
							</tr>
							<tr bgcolor="#FFFFFF" colspan="10">
								<c:forEach begin="1" end="${oneCab.cab_size }" varStatus="vs">
								
									<td width="10%" style="border:1px solid;border-color:#aec3de;" >
								
										<table>
											<tr bgcolor="#FFFFFF" >
												<td>${vs.count }<c:forEach items="${oneCab.depot }"
														var="de">
														<c:if test="${de.dep_address==vs.count}">: 已存放
												</c:if>
													</c:forEach>
												</td>
										</table>
									</td>
                               

									<c:if test="${vs.count % 10 ==0 }">
							</tr>
						<tr bgcolor="#FFFFFF">
							</c:if>
					</c:forEach>


					</tr>
					</c:forEach>






					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="showCab.do" /></td>
					</tr>
				</table>
			</td>
		</tr>

	</table>


</body>
</html>