function isIdCardNo(idNumber) {
	return /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(idNumber);
}

function isMobileNo(sMobile) {
	return /^1[34578]\d{9}$/.test(sMobile);
}

function isBankNo(bankNO) {
	return /^([0-9]{16}|[0-9]{19})$/.test(bankNO);
}