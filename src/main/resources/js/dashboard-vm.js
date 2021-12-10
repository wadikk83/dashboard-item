define('dashboard-items/velocity', ['underscore', 'jquery', 'wrm/context-path'], function (_, $, contextPath) {
    class DashboardItem {
        constructor(API) {
            this.API = API;
            this.projects = [];
            this.projectsList = [];
        }

        render(context, preferences) {
            $(context).find("#empty").hide();
            $(context).find("#config").hide();
            let $element = this.$element = $(context).find("#view").show();

            this.API.showLoadingBar();
            this.requestData(preferences).done(data => {
                this.API.hideLoadingBar();
                this.projects = data;

                if (this.projects === undefined || this.projects.length === 0) {
                    $(context).find("#config").hide();
                    $(context).find("#view").hide();
                    $(context).find("#empty").show();
                } else {
                    $(context).find("#config").hide();
                    $(context).find("#empty").hide();
                    $(context).find("#view").show();

                    $("#myTable").empty();

                    let table = document.getElementById('myTable');

                    for (let i = 0; i < data.length; i++) {
                        let row = `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].projectKey}</td>
                        <td>${data[i].fieldsList}</td>
					  </tr>`
                        table.innerHTML += row
                    }
                }
                this.API.resize();
                $element.find(".submit").click(event => {
                    event.preventDefault();
                    this.render(context, preferences);
                });
            });
        }

        requestData(preferences) {
            return $.ajax({
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(preferences),
                url: contextPath() + "/rest/dashboard-item-resource/1.0/project-cf/get-list-projects-with-cf",
                async: false,
                cache: false,
            });
        };

        renderEdit(context, preferences) {
            $(context).find("#empty").hide();
            $(context).find("#view").hide();
            let $element = $(context).find("#config").show();

            let $form = $("form", $element);
            $(".cancel", $form).click(_.bind(() => {
                if (preferences['projects-list'])
                    this.API.closeEdit();
            }, this));

            $form.submit(_.bind(event => {
                event.preventDefault();
                const preferences = this.getPreferencesFromForm($form);
                this.API.savePreferences(preferences);
                this.API.showLoadingBar();
            }, this));
        }

        getPreferencesFromForm($form) {
            const preferencesArray = $form.serializeArray();
            let preferencesObject = {};
            preferencesArray.forEach(function (element) {
                preferencesObject[element.name] = element.value;
            });
            return preferencesObject;
        }

        getRequestProjectsList() {
            let responce = [];
            $.ajax({
                url: contextPath() + "/rest/dashboard-item-resource/1.0/project-cf/get-list-projects",
                method: "GET",
                async: false,
                cache: false,
                success: function (data) {
                    responce = data;
                }
            });
            return responce;
        }
    }

    return DashboardItem;
});