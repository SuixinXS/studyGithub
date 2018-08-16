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
	   
	 
	   
	   
	   //监听
	   $(".btnAll").click(function(){
			  if($(this).html()=="【新增】"){
				  $("#dlg").window("open");
		 } 			  
		
		   	   });
	   
	   
   });
   
   
  function upCtm(id){
	  $.ajax({					
		   type:"post",       
          url:"selctm.do?cid="+id,
          dataType:"json",
          success:function(json){
       	   $("#dlg2").form('load',json);
       	   $("#dlg2").window("open");       	
             }, 
	          });
  };
  
  function stopCtm(id){
	  confirm("是否停用该客户");
	  window.location.href = "stopctm.do?cid="+id;
  }; 
  
  
  function selCtm(){


	  var selType=99;
	  var selState=99;
	  var selGrade=99;
	  var selPay=99;
	  
	  if($("#selType>option:selected").html()!="未选择客户类型"){selType=$("#selType>option:selected").val();}
	  
	  if($("#selState>option:selected").html()!="未选择账号状态"){selState=$("#selState>option:selected").val();}
	  
	  if($("#selGrade>option:selected").html()!="未选择客户等级"){selGrade=$("#selGrade>option:selected").val();}
	  
	  if($("#selPay>option:selected").html()!="未选择客户支付方式"){selPay=$("#selPay>option:selected").val();}

      window.location.href = "lsctm.do?company_type="+selType+"&ctm_state="+selState+"&ctm_type="+selGrade+"&pay_type="+selPay;	  

  };
  
  function cleaSelBox(){
	  $("#selType").val("未选择客户类型");
	  $("#selState").val("未选择账号状态");
	  $("#selGrade").val("未选择客户等级");
	  $("#selPay").val("未选择客户支付方式");
	  
  }

</script>



</head>
<body>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><table width="96%" border="0"
					align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle">客户管理
						 	
				
						<select id="selType" name="company_type">
								<option>未选择客户类型</option>							
									<option value="0" ${sessionScope.selCustomerEx.company_type==0?'selected':''}>私人</option>
									<option value="1" ${sessionScope.selCustomerEx.company_type==1?'selected':''}>公司</option>			
						</select>
						<select id="selState" name="ctm_state">
								<option>未选择账号状态</option>							
									<option value="0" ${sessionScope.selCustomerEx.ctm_state==0?'selected':''}>不可用</option>
									<option value="1" ${sessionScope.selCustomerEx.ctm_state==1?'selected':''}>可用</option>			
						</select>
						<select id="selGrade" name="ctm_type">
								<option>未选择客户等级</option>							
									<option value="0" ${sessionScope.selCustomerEx.ctm_type==0?'selected':''}>普通</option>
									<option value="1" ${sessionScope.selCustomerEx.ctm_type==1?'selected':''}>大客户</option>		
									<option value="2" ${sessionScope.selCustomerEx.ctm_type==2?'selected':''}>VIP</option>			
						</select>
						
							<select id="selPay" name="pay_type">
								<option>未选择客户支付方式</option>							
									<option value="0" ${sessionScope.selCustomerEx.pay_type==0?'selected':''}>非月结</option>
									<option value="1" ${sessionScope.selCustomerEx.pay_type==1?'selected':''}>月结</option>		
									
						</select>
						<button onclick="selCtm()">查询</button> 
						<button onclick="cleaSelBox()">清空</button> 
				
						
						</td>
						
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">客户名</td>
						<td align="center" bgcolor="#ebf0f7">客户类型</td>
						<td align="center" bgcolor="#ebf0f7">公司名</td>
						<td align="center" bgcolor="#ebf0f7">客户电话</td>
						<td align="center" bgcolor="#ebf0f7">客户识别码</td>
						<td align="center" bgcolor="#ebf0f7">客户账号状态</td>
						<td align="center" bgcolor="#ebf0f7">客户地址</td>
						<td align="center" bgcolor="#ebf0f7">客户账号等级</td>
						<td align="center" bgcolor="#ebf0f7">客户支付方式</td>

						<td align="center" bgcolor="#ebf0f7">操作 <span class="btnAll">【新增】</span>
						</td>
					</tr>
					<c:forEach items="${ctmlist}" var="oneCtm">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneCtm.ctm_name}</td>
							<td align="center">${oneCtm.company_type==0?'私人':'公司'}</td>
							<td align="center">${oneCtm.company_name}</td>
							<td align="center">${oneCtm.ctm_phone}</td>
							<td align="center">${oneCtm.ctm_sbm}</td>
							<td align="center">${oneCtm.ctm_state==0?'不可用':'可用	'}</td>
							<td align="center">${oneCtm.ctm_address}</td>
							<td align="center">${oneCtm.ctm_type==0?'普通客户':oneCtm.ctm_type==1?'大客户':oneCtm.ctm_type==2?'VIP':''}</td>
							<td align="center">${oneCtm.pay_type==0?'非月结客户':oneCtm.pay_type==1?'月结客户':''}</td>




							<td align="center"><span class="btnAll"
								onclick="upCtm(${oneCtm.ctm_id})">【编辑】</span> | <span
								class="btnAll" onclick="stopCtm(${oneCtm.ctm_id})">【停用】</span></td>
						</tr>
					</c:forEach>

					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}" url="lsctm.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>

	<div id="dlg" class="easyui-dialog" title="添加用户信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="addctm.do">
			<table>
				<tr>
					<td>客户名</td>
					<td><input name="ctm_name" /></td>
				</tr>
				<tr>
					<td>客户类型</td>
					<td><select name="company_type">
							<option value="0">私人</option>
							<option value="1">公司</option>
					</select></td>
				</tr>
				<tr>
					<td>公司名</td>
					<td><input name="company_name" /></td>
				</tr>
				<tr>
					<td>客户电话</td>
					<td><input name="ctm_phone" /></td>
				</tr>
				<tr>
					<td>客户识别码</td>
					<td><input name="ctm_sbm" /></td>
				</tr>
				<tr>
					<td>客户账号状态</td>
					<td><select name="ctm_state">
							<option value="0">不可用</option>
							<option value="1">可用</option>
					</select></td>
				</tr>
				<tr>
					<td>客户登陆名</td>
					<td><input name="ctm_login" /></td>
				</tr>
				<tr>
					<td>客户登陆密码</td>
					<td><input name="ctm_pass" /></td>
				</tr>
				<tr>
					<td>客户地址</td>
					<td><input name="ctm_address" /></td>
				</tr>
				<tr>
					<td>客户账号等级</td>
					<td><select name="ctm_type">
							<option value="0">普通客户</option>
							<option value="1">大客户</option>
							<option value="2">vip客户</option>
					</select></td>
				</tr>
				<tr>
					<td>客户支付方式</td>
					<td><select name="pay_type">
							<option value="0">非月结客户</option>
							<option value="1">月结客户</option>
					</select></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>

<div id="dlg2" class="easyui-dialog" title="编辑用户信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="upctm.do">
			<table>
				<tr>
					<td>客户名</td>
					<td><input name="ctm_name" /><input name="ctm_id" type="hidden"/></td>
				</tr>
				<tr>
					<td>客户类型</td>
					<td><select name="company_type">
							<option value="0">私人</option>
							<option value="1">公司</option>
					</select></td>
				</tr>
				<tr>
					<td>公司名</td>
					<td><input name="company_name" /></td>
				</tr>
				<tr>
					<td>客户电话</td>
					<td><input name="ctm_phone" /></td>
				</tr>
				<tr>
					<td>客户识别码</td>
					<td><input name="ctm_sbm" /></td>
				</tr>
				<tr>
					<td>客户账号状态</td>
					<td><select  name="ctm_state">
							<option value="0">不可用</option>
							<option value="1">可用</option>
					</select></td>
				</tr>
				<tr>
					<td>客户登陆名</td>
					<td><input name="ctm_login" /></td>
				</tr>
				<tr>
					<td>客户登陆密码</td>
					<td><input name="ctm_pass" /></td>
				</tr>
				<tr>
					<td>客户地址</td>
					<td><input name="ctm_address" /></td>
				</tr>
				<tr>
					<td>客户账号等级</td>
					<td><select name="ctm_type">
							<option value="0">普通客户</option>
							<option value="1">大客户</option>
							<option value="2">vip客户</option>
					</select></td>
				</tr>
				<tr>
					<td>客户支付方式</td>
					<td><select name="pay_type">
							<option value="0">非月结客户</option>
							<option value="1">月结客户</option>
					</select></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</div>



</body>
</html>