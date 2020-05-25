<b>В этом пакете находятся заглушки:</b>
<ul>
<li>АЦ в части девайс-токена;</li>
<li>АЦ в части СМС-ОТП;</li>
<li>МСА (<code>model/stubs/model/msa/msa-swagger.yaml</code>), актуального на 25.05.2020</li>
</ul>
<p>Заглушки будут доступны после запуска приложения. Эндпоинты заглушек можно найти в классе <br>
<code>ru.croc.vtb.wso2.api.tests.model.stubs.service.MockController</code></p>
<p>Заглушки также поднимаются и при запуске тестов, унаследованных от <br>
<code>ru.croc.vtb.wso2.api.tests.ProxyTest</code>
и живут столько, сколько выполняется класс теста.
</p>