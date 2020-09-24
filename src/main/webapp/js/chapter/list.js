$(function(){
    var csid = getUrlParms("csid");

    var data4Vue = {
        uri:'chapters',
        bena:{id:0,name:'',chapters:{id:0}},
        benas:[],
        product:'',
        category:'',
        kind:'',
        pagination:'',
        chapters:'',
        file:'',
        user:{id:0,name:'',fileImage:{id:0}}
    }

    var vue = new Vue({
        el:'#category',
        data:data4Vue,
        mounted:function(){
            this.getChapters(csid);
            this.list(0);
            this.getUser();
        },
        methods:{
            getChapters:function(csid){
                var url = "chapteries/" + csid;
                axios.get(url).then(function(response){
                    vue.chapters = response.data;
                    vue.product = vue.chapters.product;
                    vue.category = vue.product.category;
                    vue.kind = vue.category.kind;
                })
            },

            list:function(story){
                var url = "chapteries/" + csid + "/" + this.uri + "?stary=" + story;
                axios.get(url).then(function(response){
                    vue.benas = response.data.content;
                    vue.pagination = response.data;
                })
            },

            add:function(){
                if(!checkEmpty(this.bena.name, "章数名称"))
                    return;
                if(!checkEmpty(this.file, "章数文件"))
                    return;
                var url = this.uri + "/" + csid;
                var formData = new FormData();
                formData.append("file", this.file);
                formData.append("name", this.bena.name);

                axios.post(url, formData).then(function(response){

                    vue.list(0);
                    vue.bena = {id:0,name:'',chapters:{id:0}};
                    $("input.file").val("");
                    vue.file = null;
                });
            },
            getUser:function(){
                var url = "users";
                axios.get(url).then(function(response){
                    vue.user = response.data;
                })
            },
            deleteUser:function(){
                var url = "users";
                axios.delete(url).then(function(response){
                    location.href = "admin";
                })
            },

            deleteBena:function(id){
                if(!checkDeleteLink())
                    return;
                var url = this.uri + "/" + id;
                axios.delete(url).then(function(response){
                    if(response.data.length != 0)
                        alert(response.data);
                    else
                        vue.list(0);
                })
            },

            getFile:function(event,bool){
                this.file = event.target.files[0];
            },

            jump:function(page){
                //跳转首页、尾页、上一页、下一页
                jump(page,vue);
            },
            jumpByNumber: function(start){
                //跳转中间的部分
                jumpByNumber(start,vue);
            },
        }
    });
});