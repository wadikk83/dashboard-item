define('dashboard-items/velocity', ['underscore', 'jquery', 'wrm/context-path'], function (_, $, contextPath) {
    var DashboardItem = function (API) {
        this.API = API;
        this.projects = [];
        this.projectsList = [];
    };
    /**
     * Called to render the view for a fully configured dashboard item.
     *
     * @param context The surrounding <div/> context that this items should render into.
     * @param preferences The user preferences saved for this dashboard item (e.g. filter id, number of results...)
     */
    DashboardItem.prototype.render = function (context, preferences) {
        $(context).find("#empty").hide();
        $(context).find("#config").hide();
        let $element = this.$element = $(context).find("#view").show();

        this.API.showLoadingBar();
        let self = this;
        this.requestData(preferences).done(function (data) {
                self.API.hideLoadingBar();
                self.projects = data;

                $.each(data, function (index, value) {
                    console.log("index->" + index + " value-> " + value);
                })

                if (self.projects === undefined || self.projects.length === 0) {
                    $(context).find("#config").hide();
                    $(context).find("#view").hide();
                    $(context).find("#empty").show();

                } else {
                    $(context).find("#config").hide();
                    $(context).find("#empty").hide();
                    $(context).find("#view").show();

                    $("#myTable").empty();
                    let table = document.getElementById('myTable');

                    for (var i = 0; i < data.length; i++) {
                        var row = `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].project}</td>
                        <td>${data[i].fields}</td>
					  </tr>`
                        table.innerHTML += row
                    }

                }
                self.API.resize();
                $element.find(".submit").click(function (event) {
                    event.preventDefault();
                    self.render(context, preferences);
                });
            }
        );
    };

    DashboardItem.prototype.requestData = function (preferences) {

        return $.ajax({
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            //data: JSON.stringify(preferences['projects-list']),
            data: preferences['projects-list'],
            url: contextPath() + "/rest/dashboard-item-resource/1.0/project-cf/get-list-projects-with-cf",
            async: false,
            cache: false,
        });
    };

    /**
     * Called to render the configuration form for this dashboard item if preferences.isConfigured
     * has not been set yet.
     * This method will be called only if dashboard-item is configurable and editable
     *
     * @param context The surrounding <div/> context that this items should render into.
     * @param preferences The user preferences saved for this dashboard item
     */
    DashboardItem.prototype.renderEdit = function (context, preferences) {
        $(context).find("#empty").hide();
        $(context).find("#view").hide();
        let $element = this.$element = $(context).find("#config").show();

        let $form = $("form", $element);
        $(".cancel", $form).click(_.bind(function () {
            if (preferences['projects-list'])
                this.API.closeEdit();
        }, this));

        $form.submit(_.bind(function (event) {
            event.preventDefault();
            let preferences = getPreferencesFromForm($form);
            if (preferences['projects-list'] !== undefined && preferences['projects-list'].length !== 0) {
                this.API.savePreferences(preferences);
                this.API.showLoadingBar();
            }
        }, this));
    };

    function getPreferencesFromForm($form) {
        let preferencesArray = $form.serializeArray();
        let preferencesObject = {};
        let array = [];
        preferencesArray.forEach(function (element) {
            array.push(element.name);
        });
        preferencesObject['projects-list'] = array;

        return preferencesObject;
    }

    return DashboardItem;
});