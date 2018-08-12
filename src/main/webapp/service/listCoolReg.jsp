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
	    //监听
		   $(".btnAll").click(function(){
				  if($(this).html()=="【新增】"){
					  $("#dlg").window("open");
			 } 			  
			
			   	   });  

   });
   
   function ww3(date){ 
	   var y = date.getFullYear(); 
	   var m = date.getMonth()+1; 
	   var d = date.getDate(); 
	   var h = date.getHours(); 
	   var min = date.getMinutes(); 
	   var sec = date.getSeconds(); 
	   var str = y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+' '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec); 
	   return str; 
	  } 
	  function w3(s){ 
	   if (!s) return new Date();
	   var y = s.substring(0,4); 
	   var m =s.substring(5,7); 
	   var d = s.substring(8,10); 
	   var h = s.substring(11,14); 
	   var min = s.substring(15,17); 
	   var sec = s.substring(18,20); 
	   if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)){ 
	    return new Date(y,m-1,d,h,min,sec); 
	   } else { 
	    return new Date(); 
	   } 
	  } 

	  
	  function zfCoolReg(rid){		  
		 if(confirm("是否作废该订单？")){
		  $.ajax({
				type: "post",
				url: "zfCoolReg.do?rid=" + rid,
				dataType: "json",
				success: function(json) {
	            alert(json);
	            window.location.href = "lsColdReg.do"; //跳转
				},
			});}
		  
	  };
	  
	  function upCoolReg(rid){		  
		  $.ajax({
				type: "post",
				url: "upCoolReg.do?rid=" + rid,
				dataType: "json",
				success: function(json) {
					$("#dlg2").form('load', json);
					
					$.ajax({
						type: "post",
						url: "selgoodbyrid.do?rid=" + rid,
						dataType: "json",
						success: function(json) {
							$("#dlg2").form('load', json);
					

						},
					});
					
					
					$("#dlg2").window("open");

	            
	            
				},
			});
		  
	  };
   function selby(){

     if($("#sel>option:selected").html()=="订单ID"){
    	 
       window.location.href = "lsColdReg.do?rid="+$("#selval").val();}
		  
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
					<tr align="left" bgcolor="">
						<td colspan="10" class="optiontitle"><select id="sel">
								<option>订单ID</option>
						
						</select><input id="selval"></input>
						<button onclick="selby()">查询</button>
						</td>

					</tr>
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle">订单 <span class="btnAll">【新增】</span>
						</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">订单ID</td>
						<td align="center" bgcolor="#ebf0f7">状态</td>
						<td align="center" bgcolor="#ebf0f7">计费</td>
						<td align="center" bgcolor="#ebf0f7">打冷方式</td>

						<td align="center" bgcolor="#ebf0f7">客户</td>
						<td align="center" bgcolor="#ebf0f7">客户联系人</td>
						<td align="center" bgcolor="#ebf0f7">委托时间</td>
						<td align="center" bgcolor="#ebf0f7">结束时间</td>
						<td align="center" bgcolor="#ebf0f7">操作</td>

					</tr>
					<c:forEach items="${lsColdReg}" var="oneCold">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneCold.regist_id}</td>
							<td align="center">${oneCold.regist_state==0?'登记':oneCold.regist_state==1?'已安排打冷':oneCold.regist_state==2?'中断':oneCold.regist_state==3?'结束打冷':'作废'}</td>
							<td align="center">${oneCold.regist_paystate==1?'已付':'未付'}</td>
							<td align="center">${oneCold.cooltype==0?'付费打冷':oneCold.cooltype==1?'预付打冷':oneCold.cooltype==2?'后付打冷':'补时'}</td>
							<td align="center">${oneCold.company_name}</td>
							<td align="center">${oneCold.ctm_name}</td>
							<td align="center"><input name="regist_begin"
								readonly="readonly" class="easyui-datetimebox"
								data-options="formatter:ww3,parser:w3"
								value="<fmt:formatDate value="${oneCold.regist_begin}" pattern="yyyy-MM-dd  HH:mm:ss"/>" />
							</td>
							<td align="center"><input name="regist_end"
								readonly="readonly" class="easyui-datetimebox"
								data-options="formatter:ww3,parser:w3"
								value="<fmt:formatDate value="${oneCold.regist_end}" pattern="yyyy-MM-dd  HH:mm:ss"/>" />
							</td>
							<%-- <td align="center">${oneCold.regist_begin}</td>
							<td align="center">${oneCold.regist_end}</td> --%>

							<td align="center"><span class="btnAll" onclick="upCoolReg(${oneCold.regist_id})">【编辑】</span>
								| <span class="btnAll" onclick="zfCoolReg(${oneCold.regist_id})">【作废】</span></td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsColdReg.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>


	<div id="dlg" class="easyui-dialog" title="添加"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="addColdReg.do">
			<table>
				<tr>
					<td>客户Id</td>
					<td><input name="ctm_id" data-options="required:true"  class="easyui-validatebox" /></td>
				</tr>

				<tr>
					<td>打冷方式</td>
					<td><select name="cooltype">
					<option value="0">付费打冷</option>
					<option value="1">预付打冷</option> 
					<option value="2">后付打冷 </option>
					<option value="3">补时</option>
					</select></td>

				</tr>

				<tr>
					<td>委托时间</td>
					<td><input name="regist_begin" class="easyui-datetimebox" class="easyui-validatebox" data-options="required:true" 
						style="width: 100px" data-options="formatter:ww3,parser:w3" data-options="required:true"/></td>
				</tr>

				<tr>
					<td>结束时间</td>
					<td><input name="regist_end" class="easyui-datetimebox" class="easyui-validatebox" data-options="required:true" 
						style="width: 100px" data-options="formatter:ww3,parser:w3" data-options="required:true"/></td>
				</tr>



				<tr>
					<td>货物名</td>
					<td><input name="good_name" data-options="required:true"  class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td>货物类型</td>
					<td><select name="good_type">
							<option value="0">水果</option>
							<option value="1">海鲜</option>
							<option value="2">蔬菜</option>
					</select></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg2" class="easyui-dialog" title="编辑"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="upCoolReg1.do">
			<table>
				<tr>
					<td>客户Id</td>
					<td><input name="ctm_id" data-options="required:true" class="easyui-validatebox"  />
					<input name="regist_id" type="hidden"/></td>
				</tr>

				<tr>
					<td>打冷方式</td>
					<td><select name="cooltype">		
					<option value="0">付费打冷</option>
					<option value="1">预付打冷</option> 
					<option value="2">后付打冷 </option>
					<option value="3">补时</option></select></td>

				</tr>

				<tr>
					<td>委托时间</td>
					<td><input name="regist_begin" class="easyui-datetimebox" class="easyui-validatebox" data-options="required:true" 
						style="width: 100px" data-options="formatter:ww3,parser:w3" data-options="required:true" /></td>
				</tr>

				<tr>
					<td>结束时间</td>
					<td><input name="regist_end" class="easyui-datetimebox" class="easyui-validatebox" data-options="required:true" 
						style="width: 100px" data-options="formatter:ww3,parser:w3"  data-options="required:true"/></td>
				</tr>



				<tr>
					<td>货物名</td><input name="good_id" type="hidden"/>
					<td><input name="good_name" data-options="required:true"  class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td>货物类型</td>
					<td><select name="good_type">
							<option value="0">水果</option>
							<option value="1">海鲜</option>
							<option value="2">蔬菜</option>
					</select></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="编辑" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>