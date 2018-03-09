var util = {
    form:{
        formDataToJson:function(form) {
            var json = {};
            var formData = $(form).serializeArray();
            $.each(formData, function () {
                if(json[this.name]) {
                    if(!json[this.name].push){
                        json[this.name] = [json[this.name]];
                    }
                    json[this.name].push(this.value || '');
                }else{
                    json[this.name] = this.value || '';
                }
            });
            return json;
        },
        formDataToJsonstring:function(form){
            return JSON.stringify(this.formDataToJson(form));
        }
    }
}