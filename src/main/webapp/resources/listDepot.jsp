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
	$(function() {

		//初始化jq-easyUI对话框
		$("#dlg,dlg2").window({
			onBeforeClose : function() {
				setTimeout(function() {
					sending.style.visibility = "hidden";
				}, 200);
			}
		});
		if ('${remsg}' != null && '${remsg}' != '') {
			alert('${remsg}');
		}

		$(".btnAll").click(function() {
			if ($(this).html() == "【新增】") {
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
							url : "lscabinbyaid.do?area_id=" + area_id,
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
							url : "lscabinbyaid.do?area_id=" + area_id,
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

	function sel() {
		var cid = $("#selbycabin").val();
		location.href = "lsdepot.do?cid=" + cid;
	}

	   function delDepot(d_id){
	   if(confirm("是否刪除该仓库")){
	   location.href="deldepot.do?d_id="+d_id;} 	   
	  };
	  
     function upDepot(d_id){	   
	   $.ajax({				
		   type:"post",       
	         url:"updepot1.do?d_id="+d_id,
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

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><table width="96%" border="0"
					align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="10" class="optiontitle">冷库仓库管理<select
							id="selbyarea">
								<option selected="selected" value="0">未选择区域</option>
								<c:forEach items="${arealist}" var="area">
									<option value="${area.area_id}">${area.area_name}</option>
								</c:forEach>
						</select> <select id="selbycabin">
								<option selected="selected" value="0">全部卡位</option>

						</select>

							<button onclick="sel()">查询</button>
						</td>

					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">仓库名</td>
						<td align="center" bgcolor="#ebf0f7">仓库容量</td>
						<td align="center" bgcolor="#ebf0f7">仓库地址</td>

						<td align="center" bgcolor="#ebf0f7">仓库负责人</td>
						<td align="center" bgcolor="#ebf0f7">仓库状态</td>
						<td align="center" bgcolor="#ebf0f7">所属卡位</td>


						<td align="center" bgcolor="#ebf0f7">操作 <span class="btnAll">【新增】</span>
						</td>
					</tr>
					<c:forEach items="${depotlist}" var="oneDepot">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${oneDepot.dep_name}</td>
							<td align="center">${oneDepot.dep_capacity}</td>
							<td align="center">${oneDepot.dep_address}</td>

							<td align="center">${oneDepot.dep_director}</td>
							<td align="center">${oneDepot.dep_state}</td>
							<td align="center">${oneDepot.cab_no}</td>

							<td align="center"><span class="btnAll" onclick="upDepot(${oneDepot.dep_id})">【编辑】</span>
								| <span class="btnAll" onclick="delDepot(${oneDepot.dep_id})">【删除】</span></td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="10"><jk:page totalPage="${page.totalPage}"
								pageNo="${page.pageNo}" totalRecord="${page.totalRecord}"
								url="lsdepot.do" /></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" style="color: red">${msg}</td>
		</tr>
	</table>
	<div id="dlg" class="easyui-dialog" title="添加仓库信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="indepot.do">
			<table>
				<tr>
					<td>仓库名</td>
					<td><input name="dep_name"/></td>
				</tr>

				<tr>
					<td>仓库容量</td>
					<td><input name="dep_capacity" /></td>
				</tr>
				<tr>
					<td>仓库地址</td>
					<td><input name="dep_address" /></td>
				</tr>
				<tr>
					<td>仓库负责人</td>
					<td><input name="dep_director" /></td>
				</tr>
				<tr>
					<td>仓库状态</td>
					<td><input name="dep_state" /></td>
				</tr>
				<tr>
					<td>所属卡位</td>
					<td><select id="selbyarea1">
							<option selected="selected" value="0">未选择区域</option>
							<c:forEach items="${arealist}" var="area">
								<option value="${area.area_id}">${area.area_name}</option>
							</c:forEach>
					</select> <select id="selbycabin1" name="cab_id">
							<option selected="selected" value="0">全部卡位</option>

					</select></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</div>

<div id="dlg2" class="easyui-dialog" title="编辑仓库信息"
		data-options="iconCls:'icon-save',closed:true,modal:true"
		style="display: none; width: 400px; height: 300px; padding: 10px; top: 30px">
		<form action="updepot2.do">
			<table>
				<tr>
					<td>仓库名</td>
					<td><input name="dep_name"/><input type="hidden" name="dep_id"/></td>
				</tr>

				<tr>
					<td>仓库容量</td>
					<td><input name="dep_capacity" /></td>
				</tr>
				<tr>
					<td>仓库地址</td>
					<td><input name="dep_address" /></td>
				</tr>
				<tr>
					<td>仓库负责人</td>
					<td><input name="dep_director" /></td>
				</tr>
				<tr>
					<td>仓库状态</td>
					<td><input name="dep_state" /></td>
				</tr>
				<tr>
					<td>所属卡位</td>
					<td><select name="cab_id">
						
							<c:forEach items="${cablist}" var="oncab">
								<option value="${oncab.cab_id}">${oncab.cab_no}</option>
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="提交编辑"/></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>