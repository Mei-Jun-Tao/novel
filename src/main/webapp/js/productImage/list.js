$(function(){
   var pid = getUrlParms("pid");

   var data4Vue = {
       uri:'productImages',
       benas:[],
       product:'',
       category:'',
       kind:'',
       file:'',
       user:{id:0,name:'',fileImage:{id:0}},
       bena:{id:0,type:'Book_Image',product:{id:0}},
   }

   var vue = new Vue({
      el:'#category',
       data:data4Vue,
       mounted:function(){
          this.list();
          this.get(pid);
          this.getUser();
       },
       methods:{
          get:function(id){
              var url = "products/" + id;
              axios.get(url).then(function(response){
                  vue.product = response.data;
                  vue.category = vue.product.category;
                  vue.kind = vue.category.kind;
              })
          },

          list:function(){
              var url = "products/" + pid + "/" + this.uri + "?type=Book_Image";
              axios.get(url).then(function(response){
                  vue.benas = response.data;
              })
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

           add:function(){
               if(!checkEmpty(this.file, "书本图片"))
                   return;

              var url = this.uri + "/" + pid;
              var formData = new FormData();
              formData.append("type", "Book_Image");
              formData.append("image", this.file);
              axios.post(url, formData).then(function(response){
                  vue.list();
                  vue.bena = {id:0,type:'Book_Image',product:{id:0}};
                  $("input.file").val("");
                  vue.file = null;
              })

           },

           deleteBena:function(id){
               //确认要删除
               if(!checkDeleteLink())
                   return;
              var url = this.uri + "/" + id;
              axios.delete(url).then(function(response){
                  if(response.data.length != 0)
                      alter(response.data);
                  else
                      vue.list();
              })
           },

           getFile:function(event){
              this.file = event.target.files[0];
           }
       }
   });
});