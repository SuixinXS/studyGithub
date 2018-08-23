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
	  /*  console.log(${lsReg}); */
	  
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
   
   
		
			$("#selbyarea").change(
					function() {
						$("#selbycabin").children().remove();
						$("<option>未选择卡位</option>").appendTo($("#selbycabin"));
						if ($(this).html() != "未选择区域") {
							var area_id = $(this).val();
							$.ajax({
								type : "post",
								url : "/jk_freezer/resource/lscabinbyaid.do?area_id=" + area_id,
								dataType : "json",
								success : function(json) {

									$.each(json, function(i, v) {
									
										$(
												"<option value='"+v.cab_id+"'>"
														+ v.cab_no + "</option>")
												.appendTo($("#selbycabin"));
									});

								},
							});

						}

					});
			
			$("#selbyarea1").change(
					function() {
						$("#selbycabin1").children().remove();
						$("<option>未选择卡位</option>").appendTo($("#selbycabin1"));
						if ($(this).html() != "未选择区域") {
							var area_id = $(this).val();
							$.ajax({
								type : "post",
								url : "/jk_freezer/resource/lscabinbyaid.do?area_id=" + area_id,
								dataType : "json",
								success : function(json) {

									$.each(json, function(i, v) {
									
										$(
												"<option value='"+v.cab_id+"'>"
														+ v.cab_no + "</option>")
												.appendTo($("#selbycabin1"));
									});

								},
							});

						}

					});
   
   
	
		

	
   
   
   
   });   
	  function upEnCold(eid){		  
		  $.ajax({
				type: "post",
				url: "upEnCold1.do?eid=" + eid,
				dataType: "json",
				success: function(json) {
					$("#dlg2").form('load', json);	
					$("#saveUpAddress").val($("#upAddress").val());
					
					$("#dlg2").window("open");

	            
	            
				},
			});
		  
	  };
	  
	
	    function delEnCold(eid){	
	    	
	     if(confirm("是否刪除该计划")){
	   	  window.location.href = "delEnCold.do?eid="+eid;}
		  
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
			 window.location.href ="lsEnCold.do?jg_name="+rno+"&exe_begin="+rdate;
		   };
		   
		   
		   function showNoFinsh(){
			
		
			 window.location.href ="showNoFinsh.do";
		   };
		   
		  		  
		   function clearsel(){
			   $("#selval").val("");
			   $("#seltime").val(" ");
			   };
		  
		/* 	$("#selbyarea").change(	
					alert("1");
					function() {
						$("#selbycabin").children().remove();
						$("<option>未选择卡位</option>").appendTo($("#selbycabin"));
						if ($(this).html() != "未选择区域") {
							var area_id = $(this).val();
							$.ajax({
								type : "post",
								url : "lscabinbyaid.do?area_id=" + area_id,
								dataType : "json",
								success : function(json) {

									$.each(json, function(i, v) {
									
										$("<option value='"+v.cab_id+"'>"+ v.cab_no + "</option>").appendTo($("#selbycabin"));
									});

								},
							});

						}

					});
    */
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
						<td colspan="14" class="optiontitle">计划编号<input id="selval"<%-- <c:if test="${sessionScope.selColdReg.regist_no!='-1' }"> value="${sessionScope.selColdReg.regist_no }"</c:if> --%>
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
							<c:if test="${sessionScope.selEnCold.exe_begin!=null }">value="<fmt:formatDate value="${sessionScope.selEnCold.exe_begin }" pattern="yyyy-MM-dd  HH:mm:ss"/>"  </c:if> />
							<button onclick="showNoFinsh()">显示未完成计划</button></td>
					</tr>
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="14" class="optiontitle">入库计划 <span
							class="btnAll">【新增】</span>
						</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">计划</td>
						<td align="center" bgcolor="#ebf0f7">订单状态</td>
						<td align="center" bgcolor="#ebf0f7">计费</td>
						<td align="center" bgcolor="#ebf0f7">入库计划编号</td>
						<td align="center" bgcolor="#ebf0f7">订单编号</td>
						<td align="center" bgcolor="#ebf0f7">客户联系人</td>
						<td align="center" bgcolor="#ebf0f7">联系号码</td>
						<td align="center" bgcolor="#ebf0f7">入库委托时间</td>
						<td align="center" bgcolor="#ebf0f7">入库完成时间</td>

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
								onclick="upEnCold(${oneEnCold.exe_id})">【编辑】</span> | <span
								class="btnAll" onclick="delEnCold(${oneEnCold.exe_id})">【删除计划】</span></td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="14"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsEnCold.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>
	<script type="text/javascript">
function checkAdd(){
	var flag=false;
	$(".addMsg").html("");
	if($("#selbyarea option:selected").html()=="未选择区域"){
		$("#addMsg1").html("请选择区域");
		flag=true;
	}
	
	if($("#selbycabin option:selected").html()=="未选择卡位"){
		$("#addMsg2").html("请选择卡位");
		flag=true;
	}
	
	if($("#addAddress").val()==null ||$("#addAddress").val()=='' ){
		$("#addMsg3").html("请填地址");
		
		flag=true;
	}
	
	if($("#addDepName").val()==null ||$("#addDepName").val()=='' ){
		$("#addMsg4").html("请填仓库名/车牌号");
		flag=true;
	}
	
	$.ajax({
		type : "post",
		url : "valiDepAddress.do?cid=" + $("#selbycabin").val()+"&dAddress="+$("#addAddress").val(),
		dataType : "json",
		async:false,
		success : function(json) {
        if(json=="仓库已存在"){
        	$("#addMsg3").html("地址重复");
        	flag=true;        	
        }
        if(json=="没有该地址"){
        	$("#addMsg3").html("没有该地址");
        	flag=true;        	
        }
		},

	});
	
if(flag){

	return false;}
	
	
};
</script>

	<div id="dlg" class="easyui-dialog" title="添加"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="addEnCold.do" onSubmit="return checkAdd()">
			<table>

				<tr>
					<td>订单编号<a href="/jk_freezer/service/showReg.do"
						target="_blank" style="color: red">/订单显示</a></td>
					<td><select name="regist_id">
							<c:forEach items="${lsReg1}" var="oneReg">
								<option value="${oneReg.regist_id}">${oneReg.regist_no}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>区</td>
					<td><select id="selbyarea" name="area_id"
						class="easyui-validatebox" data-options="required:true">
							<option>未选择区域</option>
							<c:forEach items="${arealist}" var="oneArea">
								<option value="${oneArea.area_id}">${oneArea.area_name}</option>
							</c:forEach>
					</select>
					<span id="addMsg1" class="addMsg" style="color: red"></span>
					</td>

				</tr>
				<tr>
					<td>卡位<a href="/jk_freezer/resource/showCab.do"
						target="_blank" style="color: red">/卡位显示</a>
					</td>
					<td>
						<select id="selbycabin" name="cab_id">
							<option>未选择卡位</option>

					</select>
					<span id="addMsg2" class="addMsg" style="color: red"></span>
					</td>
				</tr>

				<tr>
					<td>仓库在卡位中的位置</td>
					<td><input id="addAddress" name="dep_address"
						class="easyui-validatebox" data-options="required:true" />
					<span id="addMsg3" class="addMsg" style="color: red"></span>
						</td>
						
				</tr>

				<tr>
					<td>仓库名/车牌号</td>
					<td><input id="addDepName" name="dep_name"
						class="easyui-validatebox" data-options="required:true" />
						<span id="addMsg4" class="addMsg" style="color: red"></span>
						</td>
				</tr>
				<tr>
					<td>叉车手</td>
					<td><select name="emp_id">
							<c:forEach items="${ccslist}" var="CCS">
								<option value="${CCS.emp_id}">${CCS.emp_name}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
<!-- 
				<tr>
					<td colspan="2"><span id="addMsg" style="color: red"></span></td>
				</tr> -->
				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>


	<script type="text/javascript">
function checkUp(){
	var flag=false;
	$(".upMsg").html("");
	if($("#upAddress").val()==null ||$("#upAddress").val()=='' ){
		$("#upMsg1").html("请填地址");
		flag=true; 
	}
	
	if($("#upDepName").val()==null ||$("#upDepName").val()=='' ){
		$("#upMsg2").html("请填仓库名/车牌号");
		flag=true; 
	}
	
	
	if($("#upAddress").val()!=$("#saveUpAddress").val()){
	$.ajax({
		type : "post",
		url : "valiDepAddress.do?cid=" + $("#selbycabin1").val()+"&dAddress="+$("#upAddress").val(),
		dataType : "json",
		async:false,
		success : function(json) {
        if(json=="仓库已存在"){
        	$("#upMsg1").html("地址重复");
        	flag=true;        	
        }
        
        if(json=="没有该地址"){
        	$("#upMsg1").html("没有该地址");
        	flag=true;        	
        }
		
		},

	});
	}
if(flag){
	
	return false;}
	
	
};
</script>



	<div id="dlg2" class="easyui-dialog" title="编辑"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="upEnCold2.do" onSubmit="return checkUp()">
			<table>


				<tr>
					<td>区</td>
					<td><select name="area_id" id="selbyarea1">
							<c:forEach items="${arealist}" var="oneArea">
								<option value="${oneArea.area_id}">${oneArea.area_name}</option>
							</c:forEach>
					</select></td>

				</tr>
				<tr>
					<td>卡位<a href="/jk_freezer/resource/showCab.do"
						target="_blank">/卡位显示</a></td>
					<td><select name="cab_id" id="selbycabin1"
						class="easyui-validatebox" data-options="required:true">
							<c:forEach items="${cablist}" var="oneCab">
								<option value="${oneCab.cab_id}">${oneCab.cab_no}</option>
							</c:forEach>
					</select> <input name="dep_id" type="hidden" /> <input name="exe_id"
						type="hidden" /> <input name="regist_id" type="hidden" /></td>
				</tr>

				<tr>
					<td>仓库在卡位中的位置</td>
					<td><input id="upAddress" name="dep_address" class="easyui-validatebox"
						data-options="required:true"/>
						<input id="saveUpAddress" type="hidden"></input>
						<span id="upMsg1" class="upMsg" style="color: red">
						</td>
				</tr>

				<tr>
					<td>仓库名/车牌号</td>
					<td><input id="upDepName" name="dep_name" class="easyui-validatebox"
						data-options="required:true"/><span id="upMsg2" class="upMsg" style="color: red"></td>
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
					<td colspan="2"><span id="upMsg" style="color: red"></span></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="编辑" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>