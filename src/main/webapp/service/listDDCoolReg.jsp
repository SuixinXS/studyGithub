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
	    if('${remsg}'!=null &&'${remsg}'!=''){		   
		   alert('${remsg}');
	   }
  
   });   

	  
	
	    function delEnCold(eid,op){	
	    	
	      confirm("是否完成该计划");
	 alert(eid);
	      if(op=='0'){
	    	  alert("入库");
	         window.location.href = "finshDDCold.do?exe_id="+eid; }
	      else{
	    	  alert("出库");
	    	  window.location.href = "finshDDOutCold.do?exe_id="+eid;
	      }
		  
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
				 window.location.href ="lsDDCold.do?jg_name="+rno+"&exe_begin="+rdate;
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
						<td colspan="14" class="optiontitle">计划编号<input id="selval"></input>
							委托开始日期: <input id="seltime" class="easyui-datebox"
							data-options="formatter:ww4,parser:w4" />

							<button onclick="selby()">查询</button>
							<button onclick="clearsel()">清除条件</button>
					</tr>
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="14" class="optiontitle">条件显示：委托开始日期: <input
							class="easyui-datebox" readonly="readonly"
							data-options="formatter:ww3,parser:w3"
							<c:if test="${sessionScope.selDdCold.exe_begin!=null }">value="<fmt:formatDate value="${sessionScope.selDdCold.exe_begin }" pattern="yyyy-MM-dd  HH:mm:ss"/>"  </c:if> />
							<!-- <button onclick="showNoFinsh()">显示未完成计划</button> --></td>
					</tr>
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="14" class="optiontitle">调度计划</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">计划</td>
						<td align="center" bgcolor="#ebf0f7">计划编号</td>
                        <td align="center" bgcolor="#ebf0f7">叉车手</td>
						<td align="center" bgcolor="#ebf0f7">委托时间</td>
						<td align="center" bgcolor="#ebf0f7">完成时间</td>
						<td align="center" bgcolor="#ebf0f7">区</td>
						<td align="center" bgcolor="#ebf0f7">卡位</td>
						<td align="center" bgcolor="#ebf0f7">仓在卡中的位置</td>

						<td align="center" bgcolor="#ebf0f7">货物名</td>
						<td align="center" bgcolor="#ebf0f7">操作</td>
					</tr>


					<c:forEach items="${lsDDCold}" var="oneEnCold">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneEnCold.coldopera==0?'入库':'出库'}</td>
							<td align="center">${oneEnCold.jg_name}</td>
                            <td align="center">${oneEnCold.emp_name}</td>
							<td align="center"><input name="exe_begin"
								readonly="readonly" class="easyui-datetimebox"
								data-options="formatter:ww3,parser:w3"
								value="<fmt:formatDate value="${oneEnCold.exe_begin}" pattern="yyyy-MM-dd  HH:mm:ss"/>" />

							</td>

							<td align="center"><input name="exe_end" readonly="readonly"
								class="easyui-datetimebox"
								data-options="formatter:ww3,parser:w3"
								value="<fmt:formatDate value="${oneEnCold.exe_end}" pattern="yyyy-MM-dd  HH:mm:ss"/>" /></td>


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
							<td align="center">${oneEnCold.good_name}</td>
							<td align="center"><span class="btnAll"
								onclick="delEnCold(${oneEnCold.exe_id},${oneEnCold.coldopera})">【完成计划】</span></td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="14"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsDDCold.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>







</body>
</html>