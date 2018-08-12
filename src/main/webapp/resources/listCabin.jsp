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

   //进入jq
   $(function(){ 
	  
	   //初始化jq-easyUI对话框
	   $("#dlg,dlg2").window({onBeforeClose:function(){
		   setTimeout(function(){
			   sending.style.visibility="hidden";
		   },200);
	   }});
	    if('${remsg}'!=null &&'${remsg}'!=''){		   
		   alert('${remsg}');
	   }
	   
	   $(".btnAll").click(function(){
			  if($(this).html()=="【新增】"){
				  $("#dlg").window("open");
		 } 			  
		
		   	   });
	   
	   $("#selbyarea").change(function(){
		 
			  if($(this).html()=="全部"){
			  location.href="lscabin.do?aid="+$(this).val();
				
		 }else{
			
			  location.href="lscabin.do?aid="+$(this).val();
		 }
			  
	   
	   });
  	   
   });
   
   function delCabin(cab_id){
	   confirm("是否刪除该卡位");
	   location.href="delcabin.do?cid="+cab_id;
	   
   };
   
 function upCabin(cab_id){	   
	   $.ajax({				
		   type:"post",       
          url:"selcabinbycid.do?cid="+cab_id,
          dataType:"json",
          success:function(json){
       	   $("#dlg2").form('load',json);

       	   $("#dlg2").window("open");
       	   
             }, 
	          });
   }; 
   
</script>

<%
	//判断权限 :session中取回用户，有权限继续，没权限导航去错误页
%>

</head>
<body>
	<!-- 包含等待框 -->
	<jsp:include page="../waittable.jsp" flush="true" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><table width="96%" border="0"
					align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle">冷库卡位管理 <select
							id="selbyarea">
								<option selected="selected" value="0">全部</option>
								<c:forEach items="${arealist}" var="area">
									<option value="${area.area_id}"
										${sessionScope.aId==area.area_id?'selected':''}>${area.area_name}</option>
								</c:forEach>
						</select>
						</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">卡位编号</td>
						<td align="center" bgcolor="#ebf0f7">卡位所属地区</td>

						<td align="center" bgcolor="#ebf0f7">卡位地址</td>
						<td align="center" bgcolor="#ebf0f7">卡位容量</td>
						<td align="center" bgcolor="#ebf0f7">卡位备注</td>
						<td align="center" bgcolor="#ebf0f7">卡位状态</td>

						<td align="center" bgcolor="#ebf0f7">现存仓库数</td>
						<td align="center" bgcolor="#ebf0f7">打冷仓库数</td>
						<td align="center" bgcolor="#ebf0f7">非打冷仓库数</td>

						<td align="center" bgcolor="#ebf0f7">操作 <span class="btnAll">【新增】</span>
						</td>
					</tr>
					<c:forEach items="${cabinlist}" var="oneCabin">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneCabin.cab_no}</td>


							<c:forEach items="${arealist}" var="area">
								<c:if test="${area.area_id==oneCabin.area_id}">
									<td align="center">${area.area_name}</td>
								</c:if>
							</c:forEach>
							<td align="center">${oneCabin.cab_address}</td>
							<td align="center">${oneCabin.cab_size}</td>

							<td align="center">${oneCabin.cab_remark}</td>
							<td align="center">${oneCabin.cab_state==0?'满':'未满'}</td>

							<td align="center">${oneCabin.cab_number}</td>
							<td align="center">${oneCabin.coolno}</td>
							<td align="center">${oneCabin.notcoolno}</td>



							<td align="center"><span class="btnAll"
								onclick="upCabin(${oneCabin.cab_id})">【编辑】</span> | <span
								class="btnAll" onclick="delCabin(${oneCabin.cab_id})">【删除】</span>

							</td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lscabin.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>

	<div id="dlg" class="easyui-dialog" title="添加卡位信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="incabin.do">
			<table>
				<tr>
					<td>卡位编号</td>
					<td><input name="cab_no" /></td>
				</tr>
				<tr>
					<td>卡位所属地区</td>
					<td><select name="area_id">
							<c:forEach items="${arealist}" var="area">
								<option value="${area.area_id}">${area.area_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>卡位地址</td>
					<td><input name="cab_address" /></td>
				</tr>
				<tr>
					<td>卡位容量</td>
					<td><input name="cab_size" /></td>
				</tr>
				<!-- <tr>
					<td>卡位状态</td>
					<td><input name="cab_state" /></td>
				</tr>
				<tr>
					<td>现存仓库数</td>
					<td><input name="cab_number" /></td>
				</tr>
				<tr>
					<td>打冷仓库数</td>
					<td><input name="coolno" /></td>
				</tr>
				<tr>
					<td>非打冷仓库数</td>
					<td><input name="notcoolno" /></td>
				</tr> -->
				<tr>
					<td colspan="2">卡位备注</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="3" cols="40" name="cab_remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg2" class="easyui-dialog" title="编辑区域信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="upcabin.do">
			<table>
				<tr>
					<td>卡位编号</td>
					<td><input name="cab_no" /></td>
					<input name="cab_id" type="hidden"></input>
				</tr>
				<tr>
					<td>卡位所属地区</td>
					<td><select name="area_id">
							<c:forEach items="${arealist}" var="area">
								<option value="${area.area_id}">${area.area_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>卡位地址</td>
					<td><input name="cab_address" /></td>
				</tr>
				<tr>
					<td>卡位容量</td>
					<td><input name="cab_size" /></td>
				</tr>
				<tr>
					<td>卡位状态</td>
					<td><select name="cab_state">
							<option value="0">满</option>
							<option value="1">未满</option>
					</select></td>
				</tr>
				<tr>
					<td>现存仓库数</td>
					<td><input name="cab_number" /></td>
				</tr>
				<tr>
					<td>打冷仓库数</td>
					<td><input name="coolno" /></td>
				</tr>
				<tr>
					<td>非打冷仓库数</td>
					<td><input name="notcoolno" /></td>
				</tr>
				<tr>
					<td colspan="2">卡位备注</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="3" cols="40" name="cab_remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="修改" /></td>
				</tr>
			</table>
		</form>
	</div>



</body>
</html>