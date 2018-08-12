function check() {
	if (document.myform.title.value == '') {
		alert('请输入标题');
		return false;
	}
	if (document.myform.bcateid.value == '') {
		alert('请输入新闻大类');
		return false;
	}
	if (document.myform.scateid.value == '') {
		alert('请输入新闻小类');
		return false;
	}
}