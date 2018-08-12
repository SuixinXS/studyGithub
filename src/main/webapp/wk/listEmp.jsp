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
	//转时间格式
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

	//进入jq
	$(function() {

		//初始化jq-easyUI对话框
		$("#dlg,dlg2").window({
			onBeforeClose : function() {
				setTimeout(function() {
					sending.style.visibility = "hidden";
				}, 200);
			}
		});
		$(".btnAll").click(function() {
					if ($(this).html() == "【新增】") {
						$("#dlg").window("open");
						//初始化下拉框
						$("#slctJob option").remove();
						$("#slctJob").append("<option>请选择工作角色</option>");
						//获取job信息
						$.get("lsJb.do", function(jsonArray) {
							if (jsonArray.length > 0) {
								$.each(jsonArray,function(i, n) {
								  $("#slctJob").append("<option value='"+n.job_id+"'>"+ n.job_name+ "</option>");
								});
							}
						});

					}else if($(this).html()=="【查看编辑】"){
						$("#dlg2").window("open");
						$("#UpSlctJob option").remove(); 
						$.get("lsJb.do", function(jsonArray) {
							if (jsonArray.length > 0) {
								$.each(jsonArray,function(i, n) {
								  $("#UpSlctJob").append("<option value='"+n.job_id+"'>"+ n.job_name+ "</option>");
								});
							}
						});
						  $.get("slEp.do?wk_ep_id="+$(this).prop("lang"),function(json){
							 /*   $("#dlg2 input[name='emp_id']").val(json.emp_id);
							   $("#dlg2 input[name='emp_name']").val(json.emp_name);
						       $("#dlg2 select[name='depe_id']").val(json.depe_id);
						       $("#dlg2 select[name='job_id']").val(json.job_id);
						       $("#dlg2 input[name='emp_phone']").val(json.emp_phone);
						       $("#dlg2 input[name='emp_login']").val(json.emp_login);
						       $("#dlg2 input[name='emp_pass']").val(json.emp_pass);
						       $("#dlg2 input[name='identitily']").val(json.identitily);
						       $("#dlg2 input[name='address']").val(json.address);
						       $("#dlg2 input[name='school']").val(json.school);
						       $("#dlg2 input[name='salary']").val(json.salary */
						       
						       $("#addfrom").form('load',json)
						       $("#dlg2 textarea[name='emp_remark']").val(json.emp_remark);
						       if(json.emp_sex==1){
						    	   $("#boy").prop("checked", true);
						       }else{
						    	   $("#girl").prop("checked", true);
						       }
						       $("#dlg2 input[name='intime']").val(json.intime);
						       
						    });
						  }

		});
		//修改账号状态
		$(".lgstate").change(function(event){
			var r = confirm("是否修改当前账号状态?");
			if(r==false){
				event.preventDefault();
			}
			else
			{
				$.get("upEpLgState.do?wk_ep_id="+$(this).prop("lang")+"&emp_state="+$(this).val(),function(json){
				});
				
			}
		});
		//下拉框显示部门选项
		$("#slctdp").change(function() {
			alert("lsEp.do?wk_dp_id=" + $(this).val());
			location.href = "lsEp.do?wk_dp_id=" + $(this).val();
		});
        //员工姓名查询
        $("#slktNa").click(function(){
        	alert($("#wk_ep_name").val());
        	location.href = "slktNa.do?wk_ep_name="+$("#wk_ep_name").val();
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
						<td colspan="10" class="optiontitle">土豪村停车场信息
						 <select id="slctdp">
								<option value="0">全部</option>
								<c:forEach items="${wkLsDp}" var="dp" varStatus="vs">
									<option ${sessionScope.wk_dp_id==dp.depe_id?"selected":""}
										value="${dp.depe_id}">${dp.depe_name}</option>
								</c:forEach>
						</select>
						<input name="emp_name" id="wk_ep_name" type="text" />
                        <input type="submit" id="slktNa" value="查找姓名"/>
						</td>
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">员工编号</td>
						<td align="center" bgcolor="#ebf0f7">员工名</td>
						<td align="center" bgcolor="#ebf0f7">性别</td>
						<td align="center" bgcolor="#ebf0f7">电话号码</td>
						<td align="center" bgcolor="#ebf0f7">所属部门</td>
						<td align="center" bgcolor="#ebf0f7">工作角色</td>
						<td align="center" bgcolor="#ebf0f7">登入账号</td>
						<td align="center" bgcolor="#ebf0f7">登入密码</td>
						<td align="center" bgcolor="#ebf0f7">账号状态</td>
						<td align="center" bgcolor="#ebf0f7">操作 <span class="btnAll">【新增】</span>
						</td>
					</tr>
					<c:forEach items="${wkLsEp}" var="oneEp">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneEp.emp_id}</td>
							<td align="center">${oneEp.emp_name}</td>
							<td align="center">${oneEp.emp_sex==1?"男":"女"}</td>
							<td align="center">${oneEp.emp_phone}</td>
							<td align="center">${oneEp.depe_name}</td>
							<td align="center">${oneEp.job.job_name}</td>
							<td align="center">${oneEp.emp_login}</td>
							<td align="center">${oneEp.emp_pass}</td>
							
							
							<td align="center">
							    <select  class="lgstate" lang="${oneEp.emp_id}" >
							       <option value="1" ${oneEp.emp_state==1?"selected":""} >可用</option>
							       <option value="0" ${oneEp.emp_state==0?"selected":""}>不可用</option>
							    </select>
						     <%--   <input class="lgstate" name="emp_state" type="radio" value="1"
						        <c:if test="${oneEp.emp_state==1}"> checked</c:if>
						        />可用
						       <input class="lgstate" name="emp_state" type="radio" value="0"
						        <c:if test="${oneEp.emp_state==0}"> checked</c:if>
						       />不可用 --%>
							</td>
							
							
							<td align="center"><span
								class="btnAll" lang="${oneEp.emp_id}">【查看编辑】</span>

							</td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsEp.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>

	<div id="dlg" class="easyui-dialog" title="添加员工信息"
		data-options="iconCls:'icon-save',closed:true"
		style="display: none; width: 400px; height: 500px; padding: 10px;">
		<form action="<%=basePath%>wk/addEp.do" name="addfrom" method="get">
			<table>
				<tr>
					<td>员工姓名</td>
					<td><input name="emp_name" /></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><input name="emp_sex" type="radio" checked="checked"
						value="1" />男 <input name="emp_sex" type="radio" value="2" />女</td>
				</tr>
				<tr>
					<td>电话号码</td>
					<td><input name="emp_phone" /></td>
				</tr>
				<tr>
					<td>所属部门</td>
					<td><select name="depe_id">
							<c:forEach items="${wkLsDp}" var="dp" varStatus="vs">
								<option ${sessionScope.wk_dp_id==dp.depe_id?"selected":""}
									value="${dp.depe_id}">${dp.depe_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>工作角色</td>
					<td><select id="slctJob" name="job_id">
							<option>请选择工作角色</option>
					</select></td>
				</tr>

				<tr>
					<td>登入账号</td>
					<td><input name="emp_login"></input></td>
				</tr>
				<tr>
					<td>登入密码</td>
					<td><input name="emp_pass"></input></td>
				</tr>
				<tr>
					<td>身份证</td>
					<td><input name="identitily"></input></td>
				</tr>
				<tr>
					<td>居住地</td>
					<td><input name="address"></input></td>
				</tr>
				<tr>
					<td>毕业学校</td>
					<td><input name="school"></input></td>
				</tr>
				<tr>
					<td>毕业时间</td>
					<td><input name="afterschool" class="easyui-datebox"
						data-options="formatter:ww3,parser:w3"
						style="width: 100px"  
						 ></input></td>
					
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input name="intime" class="easyui-datebox"
						data-options="formatter:ww3,parser:w3"
						style="width: 100px"></input></td>
				</tr>
				<script type="text/javascript">
					
				</script>
				<tr>
					<td>每月工资</td>
					<td><input name="salary"></input></td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="2"><textarea rows="3" cols="40" name="emp_remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg2" class="easyui-dialog" title="编辑停车场信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 500px; padding: 10px; top: 30px">
		<form action="UpEp.do" name="addfrom" id="addfrom" method="get">
			<table>
			    <tr>
					<td><input name="emp_id"  type="hidden"/></td>
				</tr>
				<tr>
					<td>员工姓名</td>
					<td><input name="emp_name" /></td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
					<input name="emp_sex" type="radio" value="1" id="boy"/>男 
					<input name="emp_sex" type="radio" value="2" id="girl"/>女
					</td>
				</tr>
				<tr>
					<td>电话号码</td>
					<td><input name="emp_phone" /></td>
				</tr>
				<tr>
					<td>所属部门</td>
					<td><select name="depe_id">
							<c:forEach items="${wkLsDp}" var="dp" varStatus="vs">
								<option value="${dp.depe_id}">${dp.depe_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>工作角色</td>
					<td><select id="UpSlctJob" name="job_id">
							<option>请选择工作角色</option>
					</select></td>
				</tr>

				<tr>
					<td>登入账号</td>
					<td><input name="emp_login"></input></td>
				</tr>
				<tr>
					<td>登入密码</td>
					<td><input name="emp_pass"></input></td>
				</tr>
				<tr>
					<td>身份证</td>
					<td><input name="identitily"></input></td>
				</tr>
				<tr>
					<td>居住地</td>
					<td><input name="address"></input></td>
				</tr>
				<tr>
					<td>毕业学校</td>
					<td><input name="school"></input></td>
				</tr>
				<tr>
					<td>毕业时间</td>
					<td><input name="afterschool" class="easyui-datebox"
						data-options="formatter:ww3,parser:w3"
						style="width: 100px"
						
						></input></td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input name="intime" class="easyui-datebox"
					    data-options="formatter:ww3,parser:w3"
						style="width: 100px"></input></td>
				</tr>
				<tr>
					<td>每月工资</td>
					<td><input name="salary"></input></td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="2"><textarea rows="3" cols="40" name="emp_remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="确定" /></td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>