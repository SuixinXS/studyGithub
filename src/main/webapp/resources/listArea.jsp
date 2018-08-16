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
       //操作返回信息
	   if('${remsg}'!=null &&'${remsg}'!=''){		   
		   alert('${remsg}');
	   }
	   	   
	   //初始化jq-easyUI对话框
	   $("#dlg,dlg2").window({onBeforeClose:function(){
		   setTimeout(function(){
			   sending.style.visibility="hidden";
		   },200);
	   }});
	   
	   
	   

	   
		
			 
	   $(".subtn").click(function(){
				  if($(this).val()=="添加"){				  					  
						 if($("#areaName").val()==null || $("#areaName").val()==''){
							alert("区域名不能为空");
							}  
		                  else{
								 $.ajax({					
									   type:"post",       
							           url:"rename.do?area_name="+ $("#areaName").val(),
							           dataType:"json",
							           success:function(json){
							           if(json=="名字已存在"){
							        	    alert(json); 
							    	        return flase; 
							        	   }						         
							        	 $("#addfrom").submit();
	                                  }, 
							            }); 
						}  
				 } 	//新增
			
			   	   });
	   
	   
	   //监听
	   $(".btnAll").click(function(){
			  if($(this).html()=="【新增】"){
				  $("#dlg").window("open");
		 } 			  
		
		   	   });
	   
	   
   });
   
   
  function upArea(id){
	  $.ajax({					
		   type:"post",       
          url:"selarebyid.do?area_id="+id,
          dataType:"json",
          success:function(json){
       	   $("#dlg2").form('load',json);
       	   $("#dlg2").window("open");
       	
             }, 
	          });
  };
  
  function delArea(id){
	  confirm("是否刪除该区域");
	  window.location.href = "delare.do?area_id="+id;
  };

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
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="7" class="optiontitle">冷库区域管理</td>
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">区域名</td>
						<td align="center" bgcolor="#ebf0f7">区域备注</td>

						<td align="center" bgcolor="#ebf0f7">操作 <span class="btnAll">【新增】</span>
						</td>
					</tr>
					<c:forEach items="${arealist}" var="oneArea">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneArea.area_name}</td>
							<td align="center">${oneArea.area_remark}</td>


							<td align="center"><span class="btnAll"
								onclick="upArea(${oneArea.area_id})">【编辑】</span> | <span
								class="btnAll" onclick="delArea(${oneArea.area_id})">【删除】</span>

							</td>
						</tr>
					</c:forEach>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>

	<div id="dlg" class="easyui-dialog" title="添加区域信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form id="addfrom" action="inare.do">
			<table>
				<tr>
					<td>区域名</td>
					<td><input name="area_name" id="areaName"/></td>
				</tr>
				<tr>
					<td colspan="2">区域备注</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="3" cols="40"
							name="area_remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="添加" class="subtn" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg2" class="easyui-dialog" title="编辑区域信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="upare.do" id="upfrom">
			<table>
				<tr>
					<td>区域名</td>
					<td><input name="area_name" id="areaName2" /><input
						name="area_id" type="hidden" /></td>
				</tr>
				<tr>
					<td colspan="2">区域备注</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="3" cols="40"
							name="area_remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="修改" /></td>
				</tr>
			</table>
		</form>
	</div>



</body>
</html>