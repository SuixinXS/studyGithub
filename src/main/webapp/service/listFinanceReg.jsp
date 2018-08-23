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

	  
//计费，异步加载相关信息，打开计费框
  function calculationReg(rid){		  
		  $.ajax({
				type: "post",
				url: "calculationReg.do?rid=" + rid,
				dataType: "json",
				success: function(json) {
					$("#dlg").form('load', json);					
					$("#dlg").window("open");	        	            
				}
			});
		  
	  }; 
	  
//完成缴费
	    function finshCalculation(rid){		  
		if(confirm("是否完成该订单的缴费")){
	    	window.location.href = "finshCalculation.do?rid=" + rid; }
	  }; 
	  
	  
	 /*  搜索条件 */
   function selby(){
	   rno=-1;
	   rdate=2999-12-29;
     if($("#selval").val()!=null && $("#selval").val()!=''){
    	 rno=$("#selval").val();
     }
	  if($("#seltime").val()!=null && $("#seltime").val()!=''){
		  rdate=$("#seltime").val();
	  }
	 window.location.href ="lsFinanceReg.do?regist_no="+rno+"&regist_begin="+rdate;
   };
   
   //搜索可用订单
   function selbystate(){

	 window.location.href ="showAvailableReg.do?";
   };
   
   function clearsel(){
	   $("#selval").val("");
	   $("#seltime").val("");
	   };
	   
   
   
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
						<td colspan="10" class="optiontitle">订单编号:<input id="selval"
							<c:if test="${sessionScope.selfinanceReg.regist_no!='-1' }"> value="${sessionScope.selfinanceReg.regist_no }"</c:if>></input>
							委托开始日期: <input id="seltime" class="easyui-datebox"
							data-options="formatter:ww4,parser:w4" />

							<button onclick="selby()">查询</button>
							<button onclick="clearsel()">清除条件</button>
					</tr>
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle">条件显示：委托开始日期: <input
							class="easyui-datebox" readonly="readonly"
							data-options="formatter:ww3,parser:w3"
							<c:if test="${sessionScope.selfinanceReg.regist_begin!=null }">value="<fmt:formatDate value="${sessionScope.selfinanceReg.regist_begin }" pattern="yyyy-MM-dd  HH:mm:ss"/>"  </c:if> />
						</td>
					</tr>

					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle">财务缴费</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">订单编号</td>
						<td align="center" bgcolor="#ebf0f7">状态</td>
						<td align="center" bgcolor="#ebf0f7">计费</td>
						<td align="center" bgcolor="#ebf0f7">金额</td>
						<td align="center" bgcolor="#ebf0f7">打冷方式</td>
						<td align="center" bgcolor="#ebf0f7">客户</td>
						<td align="center" bgcolor="#ebf0f7">客户联系人</td>
						<td align="center" bgcolor="#ebf0f7">委托时间</td>
						<td align="center" bgcolor="#ebf0f7">结束时间</td>
						<td align="center" bgcolor="#ebf0f7">操作</td>

					</tr>
					<c:forEach items="${lsColdReg}" var="oneCold">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneCold.regist_no}</td>
							<td align="center">${oneCold.regist_state==0?'登记':oneCold.regist_state==1?'已安排打冷':oneCold.regist_state==2?'中断':oneCold.regist_state==3?'结束打冷':oneCold.regist_state==4?'作废':'完成'}</td>
							<td align="center"><span
								onclick="pay(${oneCold.regist_id},this)"
								<c:if test="${oneCold.regist_paystate!=1}">style="color: red"</c:if>>${oneCold.regist_paystate==1?'已付':'未付'}</span></td>
							<td align="center">${oneCold.regist_cost}</td>
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

							<td align="center"><span class="btnAll"
								onclick="calculationReg(${oneCold.regist_id})">【计费】</span> | <span
								class="btnAll" onclick="finshCalculation(${oneCold.regist_id})">【完成缴费】</span></td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsFinanceReg.do" /></td>
					</tr>
				</table></td>
		</tr>

	</table>


	<div id="dlg" class="easyui-dialog" title="计费"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="calculationReg1.do">
			<table>
			    <tr>
					<td>订单编号</td>
					<td><input name="regist_no" data-options="required:true"
						class="easyui-validatebox" />
						<input name="regist_id" type="hidden" />
						</td>
				</tr>
			
				<tr>
					<td>委托时间</td>
					<td><input name="regist_begin" class="easyui-datetimebox"
						style="width: 200px" data-options="formatter:ww3,parser:w3" /></td>
				</tr>

				<tr>
					<td>结束时间</td>
					<td><input name="regist_end" class="easyui-datetimebox"
						style="width: 200px" data-options="formatter:ww3,parser:w3" /></td>
				</tr>
				<tr>
					<td>货物名</td>
					<td><input name="good_name" data-options="required:true"
						class="easyui-validatebox" /></td>
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
					<td colspan="2">
						<hr>
							<span>计费：</span></hr>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Regist_cost" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="写入" /></td>
				</tr>
			</table>
		</form>
	</div>





</body>
</html>