<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	background-color: #ecf4ff;
}

.dtree {
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #0000ff;
	white-space: nowrap;
}

.dtree img {
	border: 0px;
	vertical-align: middle;
}

.dtree a {
	color: #333;
	text-decoration: none;
}

.dtree a.node, .dtree a.nodeSel {
	white-space: nowrap;
	padding: 1px 2px 1px 2px;
}

.dtree a.node:hover, .dtree a.nodeSel:hover {
	color: #0000ff;
}

.dtree a.nodeSel {
	background-color: #AED0F4;
}

.dtree .clip {
	overflow: hidden;
}

</style>
<link href="css/four.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<table width="96%" border="0" cellpadding="10" cellspacing="0"
		align="center">
		<tr>
			<td valign="top">
				<div class="menu">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><script type="text/javascript">
               
							d = new dTree('d');
							  d.config.target = "main";
							  					
							  d.add(0, -1, '靖凯开源');		
							  
						/*       d.add(100000,0, '冷库管理', '');
			
						      d.add(100100,100000, '冷库区域', 'resource/lsare.do');
						      d.add(100200,100000, '冷库卡位', 'resource/lscabin.do');
						      d.add(100300,100000, '冷库仓库', 'resource/lsdepot.do');
						      
						      d.add(110000,0, '用户管理', '');
						      
						      d.add(110100,110000, '客户管理', '');
						      d.add(110200,110000, '员工管理', 'wk/lsEp.do');
						      
						      d.add(120000,0, '权限管理', '');
						      
						      d.add(120100,120000, '权限编辑', '');
						      d.add(120200,120000, '权限赋予1（员工职位改变）', '');
						      d.add(120300,120000, '权限赋予2（职位权限改变）', 'sys/lsJb.do');
						      
						      d.add(130000,0, '业务管理', '');
						      
						      d.add(130100,130000, '入库（登记）', '');
						      d.add(130200,130000, '出库（登记）', '');
						      d.add(130300,130000, '叉车计划（完成已登记的入库出库计划）', ''); */
						      $(document).ready(function(){
								  $.get("fz/genPw.do",function(jsonArray){
									  if(jsonArray){
										  
										  $.each(jsonArray,function(i,v){
											  console.log(i+" : "+v.uri);
											  if(v.uri){
												  d.add(i,v.prtNo,v.text,v.uri);
											  }else{
												  d.add(i,v.prtNo,v.text,'');
											  }
										  })
									  }
									  document.write(d);
								  })
							  })
							</script></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>