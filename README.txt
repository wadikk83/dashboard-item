You have successfully created an Atlassian Plugin!

Here are the SDK commands you'll use immediately:

* atlas-run   -- installs this plugin into the product and starts it on localhost
* atlas-debug -- same as atlas-run, but allows a debugger to attach at port 5005
* atlas-help  -- prints description for all commands in the SDK

Full documentation is always available at:

https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK

Первым делом всё закомментареное и забэкапленное - выброси
MyPluginComponent, его реализацию и конфиг - выброси
рест контроллер - пути урлов должны быть более осознанными, PluginService - название поменять и инициализацию сделать всё таки нормальной),
@RequestBody распарсить нормально объектами, а не как сделано через split.
Нужна обработка ошибок, её в проекте вообще нет)
Нужен lombok
Xml модели будут падать, если что-то не придёт, или придёт не то.
PluginService - получение CustomFieldmanager и ProjectManager сделай через ComponentInport или другую аннотацию, projectFieldsMap - в мусор
Конвертация объекта Project, в энтити, а потом в модель - не замного ли?
Маппер реализуй готовыми инструментами, в 2021 нет смысла так кодить без ломбока и какого нибудь MapStruct
конктекст провайдер - там есть лишняя информация, её в мусор.
Css файл - пустой, в мусор
JS переведи на ecmascript 6 хотя бы, лишнюю логику в мусор, но там поблажка, считай не смотрю в реализацию