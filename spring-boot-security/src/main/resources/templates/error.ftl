<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<#assign home><@spring.url relativeUrl="/foo"/></#assign>
<#assign bootstrap><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
<link rel="stylesheet" href="${bootstrap}" />
</head>
<body>
	<div class="container">
		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="http://freemarker.org/"> Security安全 </a>
				<ul class="nav">
					<li><a href="${home}"> 点击刷新 </a></li>
				</ul>
			</div>
		</div>
		<h1>Error Page</h1>
		<div id="created">${timestamp?datetime}</div>
		<div>
			意外的错误信息： (type=${error}, status=${status}).
		</div>
		<div>${message}</div>
	</div>
</body>
</html>
