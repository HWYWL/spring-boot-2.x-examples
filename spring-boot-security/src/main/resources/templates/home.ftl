<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<#assign home><@spring.url relativeUrl="/"/></#assign>
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
		<h1>${title}</h1>
		<div>${message}</div>
		<div id="created">${date?datetime}</div>
	</div>
</body>
</html>
