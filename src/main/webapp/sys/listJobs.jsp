<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
//进入jq
$(function(){
	   
	   //初始化jq-easyUI对话框
	   $("#dlg,dlg2").window({onBeforeClose:function(){
		   setTimeout(function(){
			   sending.style.visibility="hidden";
		   },200);
	   }});
	   
	   //监听
	  $(".btnAll").click(function(){
		  if($(this).html()=="【新增角色】"){
			  $("#dlg").window("open");
		  }else if($(this).html()=="【删除】"){
			  if(confirm("是否删除该角色信息")){
				  location.href="deleJb.do?job_id="+$(this).prop("lang");
			  }
		  }
	  }); 
		   
	  //异步验证角色名字
	/*   $("#yz_job_name").mouseout(function(){
		  $.get("yzJbNa.do?job_name="+$(this).val(),function(msg){
			  if(msg==1){
				  $("#yz_job_nameMsg").val("此角色名可用");
			  }else(msg==0){
				  $("#yz_job_nameMsg").val("已有次角色名!!!!!");
			  }
			
		  });
	  }); */
	     $("#yz_job_name").focusout(function(){
	    	 $.get("yzJbNa.do?job_name="+$(this).val(),function(msg){
	    		 if(msg[0]==0){
	    		  $("#yz_job_nameMsg").html("此角色名不可用");
	    		 }
	    		 else{
	    			  $("#yz_job_nameMsg").html("此角色可用");
	    		 }
	    	 });
	     });
	  //模糊查询角色名
	  $("#slckJbNa").click(function(){
      	location.href = "slctLKJbNa.do?jbname="+$("#jbname").val();
      });
	   
});

</script>


</head>
<body>
	<!-- 包含等待框 -->
	<jsp:include page="../waittable.jsp" flush="true" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><table width="96%" border="0"
					align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="4" class="optiontitle">角色权限信息 &nbsp; &nbsp; 
						    	<input  id="jbname"></input>
						    	<input type="submit" value="查询角色名" id="slckJbNa"/>
						</td>
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">角色名</td>
					    <td align="center" bgcolor="#ebf0f7">已有权限</td>
						<td align="center" bgcolor="#ebf0f7">操作 <span class="btnAll">【新增角色】</span></td>
						
					</tr>
					
					<c:forEach items="${listJob}" var="onejob">
					<form action="upJbPr.do">	
					<input type="hidden" value="${onejob.job_id}" name="job_id"> </input>
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${onejob.job_name}</td>
							<td align="center">
							<c:forEach items="${allPower}" var="Pr" varStatus="vs">
							   <input type="checkbox" name="power_id"

                                   <c:forEach items="${onejob.powers }" var="onePr" varStatus="va">
                                       <c:if test="${onePr.power_id==Pr.power_id }">
                                          checked
                                       </c:if>
                                   </c:forEach>
							   value="${Pr.power_id}">${Pr.power_name}</input>
							   <c:if test="${ vs.count%5==0}">
							      <br />
							   </c:if>
							   
							</c:forEach>
							<input type="submit" value="修改"></input>
							</td>
						    
							<td align="center">
								
								| <span class="btnAll" lang="${onejob.job_id}">【删除】</span>

							</td>
						</tr>
				    </form>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="4"><jk:page totalPage="${page.totalPage}" pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsPking.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>

	<div id="dlg" class="easyui-dialog" title="添加角色信息"
		data-options="iconCls:'icon-save',closed:true"
		style="display: none; width: 400px; height: 200px; padding: 10px;">
		<form action="addJb.do" name="addfrom" method="get">
			<table>
				<tr>
					<td>角色名</td>
					<td><input  id="yz_job_name" name="job_name" />
					    <span id="yz_job_nameMsg" style="color:red "></span>
					</td>
				</tr>
				<tr>
					<td>选择权限</td>
					<td>
					<c:forEach items="${allPower}"  var="pr">
					<input type="checkbox" name="power_id" value="${pr.power_id}" />${pr.power_name }
					</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>