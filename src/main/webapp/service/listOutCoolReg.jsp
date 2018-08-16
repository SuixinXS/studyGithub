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
   
   //订单验证
		   $("#addTab").find("input[name='regist_id']").blur(
			function(){
				var a=$("#addTab").find("input[name='regist_id']").val();
				if(a!=null && a!=''){
					$.ajax({
						type: "post",
						url: "checkByRid.do?rid=" + a,
						dataType: "json",
						success: function(json) {
							if(json!='通过'){
								alert(json);	
								$("#addTab").find("input[name='regist_id']").val("");
																							}																					
			
						},
					});
					
				}
			}	   
		   );
   
   
   
   
   
   
   
   
   
   
   
   
   });   
/* 	  function upEnCold(eid){		  
		  $.ajax({
				type: "post",
				url: "upEnCold1.do?eid=" + eid,
				dataType: "json",
				success: function(json) {
					$("#dlg2").form('load', json);													
					$("#dlg2").window("open");

	            
	            
				},
			});
		  
	  }; */
	  
	
	    function delEnCold(eid){	
	    	
	      if(confirm("是否刪除该计划")){
	   	  window.location.href = "delOutColdReg.do?exe_id="+eid;}
		  
	  };
	  
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

	
		  
		  
		  
		  
		  
 function ww4(date){ 
			   var y = date.getFullYear(); 
			   var m = date.getMonth()+1; 
			   var d = date.getDate(); 
			   var h = date.getHours(); 
			   var min = date.getMinutes(); 
			   var sec = date.getSeconds(); 
			   var str = y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d); 
			   return str; 
			  } 
			  function w4(s){ 
			   if (!s) return new Date();
			   var y = s.substring(0,4); 
			   var m =s.substring(5,7); 
			   var d = s.substring(8,10); 
			   var h = s.substring(11,14); 
			   var min = s.substring(15,17); 
			   var sec = s.substring(18,20); 
			   if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)){ 
			    return new Date(y,m-1,d); 
			   } else { 
			    return new Date(); 
			   } 
			  }  

			  function selby(){
				   rno=-1;
				   rdate=2999-12-29;
			     if($("#selval").val()!=null && $("#selval").val()!=''){
			    	 rno=$("#selval").val();
			     }
				  if($("#seltime").val()!=null && $("#seltime").val()!=''){
					  rdate=$("#seltime").val();
				  }
				 window.location.href ="lsOutCold.do?jg_name="+rno+"&exe_begin="+rdate;
			   };
			   
			   
			   function showNoFinsh(){
				
			
				 window.location.href ="showNoFinshOut.do";
			   };	  

			  function clearsel(){
				   $("#selval").val("");
				   $("#seltime").val(" ");
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


					<tr align="left" bgcolor="">
						<td class="optiontitle">计划总数:</td>
						<td colspan="13" class="optiontitle">${page.totalRecord }</td>
					</tr>

					<tr align="left" bgcolor="">
						<td colspan="14" class="optiontitle">计划编号:<input id="selval"<%-- <c:if test="${sessionScope.selColdReg.regist_no!='-1' }"> value="${sessionScope.selColdReg.regist_no }"</c:if> --%>
							></input>
							委托开始日期: <input id="seltime" class="easyui-datebox"
							data-options="formatter:ww4,parser:w4" />

							<button onclick="selby()">查询</button>
							<button onclick="clearsel()">清除条件</button>
					</tr>
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="14" class="optiontitle">条件显示：委托开始日期: <input
							class="easyui-datebox" readonly="readonly"
							data-options="formatter:ww3,parser:w3"
							<c:if test="${sessionScope.selOutCold.exe_begin!=null }">value="<fmt:formatDate value="${sessionScope.selOutCold.exe_begin }" pattern="yyyy-MM-dd  HH:mm:ss"/>"  </c:if> />
							<button onclick="showNoFinsh()">显示未完成计划</button></td>
					</tr>



					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="14" class="optiontitle">出库计划 <span
							class="btnAll">【新增】</span>
						</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">计划</td>
						<td align="center" bgcolor="#ebf0f7">订单状态</td>
						<td align="center" bgcolor="#ebf0f7">计费</td>
						<td align="center" bgcolor="#ebf0f7">出库计划编号</td>
						<td align="center" bgcolor="#ebf0f7">订单编号</td>
						<td align="center" bgcolor="#ebf0f7">客户联系人</td>
						<td align="center" bgcolor="#ebf0f7">联系号码</td>
						<td align="center" bgcolor="#ebf0f7">出库委托时间</td>
						<td align="center" bgcolor="#ebf0f7">出库完成时间</td>

						<td align="center" bgcolor="#ebf0f7">叉车手</td>
						<td align="center" bgcolor="#ebf0f7">区</td>
						<td align="center" bgcolor="#ebf0f7">卡位</td>
						<td align="center" bgcolor="#ebf0f7">仓在卡中的位置</td>
						<td align="center" bgcolor="#ebf0f7">操作</td>

					</tr>
					<c:forEach items="${lsEnCold}" var="oneEnCold">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneEnCold.coldopera==0?'入库':'出库'}</td>
							<td align="center">${oneEnCold.regist_state==0?'登记':oneEnCold.regist_state==1?'已安排打冷':oneEnCold.regist_state==2?'中断':oneEnCold.regist_state==3?'结束打冷':oneEnCold.regist_state==4?'作废':'完成'}</td>
							<td align="center">${oneEnCold.regist_paystate==1?'已付':'未付'}</td>
							<td align="center">${oneEnCold.jg_name}</td>
							<td align="center">${oneEnCold.regist_no}</td>
							<td align="center">${oneEnCold.ctm_name}</td>
							<td align="center">${oneEnCold.ctm_phone}</td>
							<td align="center"><input name="exe_begin"
								readonly="readonly" class="easyui-datetimebox"
								data-options="formatter:ww3,parser:w3"
								value="<fmt:formatDate value="${oneEnCold.exe_begin}" pattern="yyyy-MM-dd  HH:mm:ss"/>" />

							</td>

							<td align="center"><input name="exe_end" readonly="readonly"
								class="easyui-datetimebox"
								data-options="formatter:ww3,parser:w3"
								value="<fmt:formatDate value="${oneEnCold.exe_end}" pattern="yyyy-MM-dd  HH:mm:ss"/>" /></td>

							<td align="center">${oneEnCold.emp_name}</td>
							<td align="center"><c:forEach items="${arealist}"
									var="oneArea">
									<c:if test="${oneEnCold.area_id==oneArea.area_id}">${oneArea.area_name}</c:if>

								</c:forEach></td>

							<td align="center"><c:forEach items="${cablist}"
									var="oneCab">
									<c:if test="${oneEnCold.cab_id==oneCab.cab_id}">${oneCab.cab_no}</c:if>

								</c:forEach></td>


							<td align="center"><c:forEach items="${delist}" var="oneDep">
									<c:if test="${oneEnCold.dep_id==oneDep.dep_id}">${oneDep.dep_address}</c:if>

								</c:forEach></td>

							<td align="center"><span class="btnAll"
								onclick="delEnCold(${oneEnCold.exe_id})">【删除计划】</span></td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="14"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lscabin.do" /></td>
					</tr>
				</table></td>
		</tr>

	</table>


	<div id="dlg" class="easyui-dialog" title="添加"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="addOutColdReg.do">
			<table id="addTab">

				<tr>
					<td>订单编号</td>
					<td>
						<!-- <input name="regist_id" class="easyui-validatebox" data-options="required:true"/> -->
						<select name="regist_id">
							<c:forEach items="${lsFinEnReg }" var="oneFinEnReg">
								<option value="${oneFinEnReg.regist_id}">${oneFinEnReg.regist_no}</option>
							</c:forEach>
					</select>

					</td>


				</tr>

				<tr>
					<td>叉车手</td>
					<td><select name="emp_id">
							<c:forEach items="${ccslist}" var="CCS">
								<option value="${CCS.emp_id}">${CCS.emp_name}</option>
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>




</body>
</html>