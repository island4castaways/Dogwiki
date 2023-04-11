<%@ include file="/resource/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<title>가입성공 여부</title>
	</head>
		<body>
			<script>
			if('OK' == '${result}' ){
				alert("회원 가입을 축하합니다.");
				location.href='user_login';
			}else if('Fail' == '${result}'){
				alert("회원 가입을 실패했습니다.")
				history.go(-1);
			}else if('idExist!' == '${result}'){
				alert("아이디가 이미 존재합니다.")
				history.go(-1);
			}
	</script>
<%@ include file="../include/footer.jsp"%>