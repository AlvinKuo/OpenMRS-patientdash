<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="dicomecg.idgen" />
</h2>

<html>
<head>
<meta http-equiv="Content-Type" CONTENT="text/html; charset=big5">
<title>Taiwan ROC ID Generator</title>
<script language="JavaScript" type="text/javascript">
<!--
	function calsex1(selectForm)
	/* 計算 sex 的加權值 */
	{
		var ret = 0;
		var i = 0;
		for (i = 0; i < selectForm.a.length; i++) {
			if (selectForm.a[i].checked == true) {
				ret = ret + eval(selectForm.a[i].value) * 8;
			}
		}
		return ret;
	}
	function myvoid() {
		return false;
	}
	function myisDigit(num)
	/* isDigit function */
	{
		var str1 = "1234567890";
		var in15 = 0;
		if (num.length > 1 || num.lengtn <= 0) {
			return false;
		}
		in15 = str1.indexOf(num);
		if (in15 >= 0 && in15 <= 9) {
			return true;
		}
		return false;
	}

	function checkdigitString(s)
	/* check a string is 'digit' string or not */
	{
		var i;
		for (i = 0; i < s.length; i++) {
			if (!myisDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	function midrand1(mid11) {
		var gen1;
		gen1 = (Math.random());
		mid11.a.value = (gen1.toString()).substring(2, 9);
		return gen1;
	}

	function calmid1(selectForm)
	/* 計算中間值的加權值 */
	{
		var ret = 0;
		var i;
		for (i = 0; i < selectForm.a.value.length; i++) {
			ret = ret + (7 - i) * eval(selectForm.a.value.substring(i, i + 1));
		}
		return ret;
	}

	function calcity1(selectForm)
	/* 計算縣市的加權值 */
	{
		var ret = 0;
		var i;
		for (i = 0; i < selectForm.a.length; i++) {
			if (selectForm.a[i].selected == true) {
				ret = eval(selectForm.a[i].value.substring(0, 1))
						+ eval(selectForm.a[i].value.substring(1, 2)) * 9;
			}
		}
		return ret;
	}
	function calall(city11, sex11, mid11)
	/* 計算所有的加權值 */
	{
		var ret = 0;
		ret = calcity1(city11) + calsex1(sex11) + calmid1(mid11);
		ret = ret % 10;
		ret = 10 - ret;
		ret = ret % 10;
		return ret;
	}
	function output1(city11, sex11, mid11)
	/* 輸出檢查碼 */
	{
		var cee = 0;
		cee = calall(city11, sex11, mid11);
		return cee;
	}
	function output2(city11, sex11, mid11, output11, output12) {
		var out1 = "";
		var ret2 = 0;
		var out2 = "";
		var i;
		clearout001(output12);
		midrand1(mid11);
		ret2 = output1(city11, sex11, mid11);
		out2 = ret2.toString();
		for (i = 0; i < city11.a.length; i++) {
			if (city11.a[i].selected == true) {
				out1 = out1 + city11.a[i].value.substring(2, 3);
			}
		}

		for (i = 0; i < sex11.a.length; i++) {
			if (sex11.a[i].checked == true) {
				out1 = out1 + sex11.a[i].value;
			}
		}
		out1 = out1 + mid11.a.value;
		out1 = out1 + out2;
		output11.a.value = out1;
		return out1;
	}
	function clearout001(id04124) {
		id04124.a.value = "";
	}
	function output3(id001, out001, city11, sex11, mid11) {
		var thestr = id001.a.value;
		var i = 0;
		var ret2 = 0;
		out001.a.value = "分析中 analyzing...";
		if (thestr.length != 10) {
			out001.a.value = "invalid(length)";
			return 0;
		}
		/* 分析縣市 */
		for (i = 0; i < city11.a.length; i++) {
			if (city11.a[i].value.substring(2, 3) == thestr.substring(0, 1)
					.toUpperCase()) {
				city11.a[i].selected = true;
				break;
			}
		}
		if (i >= city11.a.length) {
			out001.a.value = "invalid(city)";
			return 0;
		}
		/* 分析性別 */
		if (thestr.substring(1, 2) == "1") {
			sex11.a[0].checked = true;
		} else if (thestr.substring(1, 2) == "2") {
			sex11.a[1].checked = true;
		} else {
			out001.a.value = "invalid(gender)";
			return 0;
		}
		/* 分析中間值 */
		mid11.a.value = thestr.substring(2, 9);
		if (!checkdigitString(mid11.a.value)) {
			out001.a.value = "invalid(serial)";
			return 0;
		}

		/* 計算檢查碼 */
		ret2 = output1(city11, sex11, mid11);

		if (ret2.toString() != thestr.substring(9, 10)) {
			out001.a.value = "invalid(check)";
			return 0;
		}
		out001.a.value = "valid";
		return 0;
	}
// -->
</script>
</head>
<body>
	<h1>ID Generator</h1>
	<h2>Create Generation</h2>
	<table border="1">
		<tr>
			<th>City :</th>
			<td>
				<form name="city" action="#">
					<select name="a">
						<option value="10A">Taipei(A)</option>
						<option value="11B">Taichung(B)</option>
						<option value="12C">Keelung(C)</option>
						<option value="13D">Tainan(D)</option>
						<option value="14E">Kaohsiung (E)</option>
						<option value="15F">New Taipei(F)</option>
						<option value="16G">Yilan(G)</option>
						<option value="17H">Taoyuan(H)</option>
						<option value="18J">Hsinchu(J)</option>
						<option value="19K">Miaoli(K)</option>
						<option value="20L">Taichung shain(L)</option>
						<option value="21M">Nantou (M)</option>
						<option value="22N">Changhua(N)</option>
						<option value="23P">Yunlin(P)</option>
						<option value="24Q">Chiayi(Q)</option>
						<option value="25R">臺南縣(R)</option>
						<option value="26S">高雄縣(S)</option>
						<option value="27T">Pingtung(T)</option>
						<option value="28U">Hualien(U)</option>
						<option value="29V">Taitung(V)</option>
						<option value="30X">Penghu(X)</option>
						<option value="31Y">Yangmingshan(Y)</option>
						<option value="32W">Kinmen(W)</option>
						<option value="33Z">Liánjiāng(Z)</option>
						<option value="35O">新竹市(O)</option>
						<option value="34I">嘉義市(I)</option>
					</select>
				</form>
			</td>
			<th>Gender <br>:
			</th>
			<td>
				<form name="sex" action="#">
					<input type="radio" name="a" value="1" checked>Male(1) <input
						type="radio" name="a" value="2">Female(2)
				</form>
			</td>
			<th>Serial :
			</th>
			<td>
				<form name="mid" action="#">
					<input type="text" name="a" value="random" size="7">
				</form>
			</td>
		<tr>
			<td colspan="6">
				<form name="gogogogoo12123" action="#">
					<input type="button" value="Generate"
						onClick="javascript:output2(city,sex,mid,idoutput1,idoutput2);">
				</form>
			</td>
		</tr>
	</table>
	<br>
	<hr noshade>
	<br>
	<h2>Verification</h2>
	<table border="1">
		<tr>
			<th>ID Number<br>:
			</th>
			<td>
				<form name="idoutput1" action="#"
					onSubmit="javascript:output3(idoutput1,idoutput2,city,sex,mid);return false;">
					<input type="text" name="a" size="10">
				</form>
			</td>
			<td>
				<form name="gogogogoo12124" action="#">
					<input type=button value="Check"
						onClick="javascript:output3(idoutput1,idoutput2,city,sex,mid);">
					
				</form>
			</td>
		</tr>
	</table>
	<form name="idoutput2" action="#">
		Correct? <input type="text" name="a" size="30">
	</form>
	<br>
	<hr noshade>
	<br> Copy from http://people.debian.org/~paulliu/ROCid.html
	<br> Copyright (C) 1999 Ying-Chun Liu (PaulLiu). All rights
	reserved.
	<br> Version: Sun Jan 19 08:14:59 CST 2003
	<br>
	
	
	
	
</body>
</html>


<%@ include file="/WEB-INF/template/footer.jsp"%>