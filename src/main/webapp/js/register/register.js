$(function(){
    var data4Vue = {
        uri:'users',
        bena:{id:0,name:'',password:'',realname:'',cellNumber:'',emallAddress:'',address:'',postalCode:''},
        emptys:'',
        isbool:0
    }

    var vue = new Vue({
        el:'#regis',
        data:data4Vue,
        methods:{
            ist:function(){
                if(!isEmpty("verify", "两次邮箱地址不一致", vue.bena.emallAddress, vue)){
                    empty(vue,2);
                    return true;
                }
                if(!isEmpty("menus", "两次密码不一致", vue.bena.password, vue)){
                    empty(vue,4);
                    return true;
                }
                $("span.glyphicon-exclamation-sign").hide();
            },
            add:function(){
                if(!userEmpty(this.bena.emallAddress, "邮箱地址不能为空", vue)){
                    empty(vue,1);
                    return;
                }
                if(!userEmpty(this.bena.password, "密码不能为空", vue)){
                    empty(vue,3);
                    return;
                }
                if(!userEmpty(this.bena.name, "账号不能为空", vue)){
                    empty(vue,5);
                    return;
                }
                if(!userEmpty(this.bena.realname, "真实名不能为空", vue)){
                    empty(vue,6);
                    return;
                }
                if(!userEmpty(this.bena.cellNumber, "手机号不能为空", vue)){
                    empty(vue,7);
                    return;
                }
                if(!userEmpty(this.bena.address, "所在地址不能为空", vue)){
                    empty(vue,8);
                    return;
                }
                if(!userEmpty(this.bena.postalCode, "邮政编码不能为空", vue)){
                    empty(vue,9);
                    return;
                }
                if(this.ist())
                    return;
                var url = this.uri;
                axios.post(url, this.bena).then(function(response){
                    vue.emptys = response.data;
                    if(response.data.length == 0){
                        location.href = "adminLogin";
                    }else{
                        vue.isbool = 5;
                        $("span#" + this.isbool).show();
                    }
                })
            }
        }
    })
})