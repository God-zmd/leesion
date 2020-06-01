<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<lesson:page title="欢迎">

    <jsp:attribute name="script">
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>
        <script type="application/javascript">
            $('.restaurant-select-toggle').on('click', function (e) {
                e.preventDefault();
                selectRestaurant("input[name='cityId']", function(restaurant){
                    console.log("update data for selected restaurant:" + JSON.stringify(restaurant));
                    if(restaurant){
                        $("#restaurant").val(restaurant.restaurantName);
                    }
                })
            });
            $('.hotel-select-toggle').on('click', function (e) {
                e.preventDefault();
                selectHotel("input[name='cityId']", function(hotel){
                    console.log("update data for selected hotel:" + JSON.stringify(hotel));
                    if(hotel){
                        $("#hotel").val(hotel.hotelName);
                    }
                })
            });

            $('.hotel-search-toggle').on('click', function (e) {
                e.preventDefault();
                searchBaseHotel("input[name='cityId']", function(hotel){
                    console.log("update data for selected hotel:" + JSON.stringify(hotel));
                    if(hotel){
                        $("#hotelId").val(hotel.hotelName);
                    }
                })
            });

            $('.scene-select-toggle').on('click', function (e) {
                e.preventDefault();
                selectScene("input[name='cityId']", function(scene){
                    console.log("update data for selected scene:" + JSON.stringify(scene));
                    if(scene){
                        $("#scene").val(scene.sceneName);
                    }
                })
            });

            $("body").on("click", ".ajax-modal-full-demo", function () {

                //AjaxModal加载数据的请求参数
                var reqVO = {
                    url:"provider/restaurant/select.do",
                    data: {city:$("input[name='cityId']").val()}
                };
                //配置文件选传
                new AjaxModal("test-modal", reqVO, function ($modal) {

                    //这是modal加载数据成功后的回调函数，选传，因为是示例，这里只绑定了table中，展示详情的点击事件
                    $modal.find('.show-details-btn').click(function (e) {
                        e.preventDefault();
                        $(this).closest('tr').next().toggleClass('open');
                        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
                    });
                }).title("Hello Modal").titleAlignCenter().initFooter().addCancelBtn("取消", function () {
                    alert("我是点了取消的回调函数，这个函数不是必传的，不传则直接隐藏modal");
                }).addDangerBtn("红色", function () {
                    alert("我是中止按钮的回调，我没有配置自动隐藏modal");
                }).addWarningBtn("黄色").addSuccessBtn("绿色", function () {
                    alert("我是绿色按钮的回调，我没有配置自动隐藏modal");
                }).addPrimaryBtn("确认", function () {
                    alert("我点了确认，我设置为怎么隐藏modal");
                }, true).show();
            });

            $("body").on("click", ".ajax-modal-show-demo", function () {

                //AjaxModal加载数据的请求参数
                new AjaxModal("simple-show-modal", {
                    url:"provider/hotel/select/hotels.do",
                    data: {city:$("input[name='cityId']").val()}
                }).title("Hello Modal").titleAlignCenter().show();
            });

        </script>
    </jsp:attribute>
    <jsp:body>
        <%@include file="/WEB-INF/views/common/topMenu.jsp" %>
        <h1>${msg}</h1>

        <form class="form-horizontal" action="file/upload.do" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="restaurant">City</label>
                <div class="col-sm-9">
                    <lesson:citySelect fieldName="cityId" selectedCityId="35" width="100%" withNone="true" withAll="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="restaurant">餐厅</label>
                <div class="col-sm-9">
                    <input type="text" autocomplete="off" id="restaurant" placeholder="餐厅" class="col-xs-10 col-sm-5 restaurant-select-toggle" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="hotel">酒店</label>
                <div class="col-sm-9">
                    <input type="text" autocomplete="off" id="hotel" placeholder="酒店选择" class="col-xs-10 col-sm-5 hotel-select-toggle" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="scene">景点</label>
                <div class="col-sm-9">
                    <input type="text" autocomplete="off" id="scene" placeholder="景点选择" class="col-xs-10 col-sm-5 scene-select-toggle" >
                </div>
            </div>
          <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="scene">Ajax Modal</label>
            <div class="col-sm-9">
              <span class="btn btn-primary ajax-modal-full-demo">异步Modal</span>
                <span class="btn btn-primary ajax-modal-show-demo">只做数据展示的Modal</span>
            </div>
          </div>
        </form>
        <h2>
            welcome. <spring:message code="common.title" text="default text" />
        </h2>
        
         <form class="transferform" action="transfer/trip.do" method="post" >
         <input type="text" autocomplete="off" id="tripId" placeholder="tripId" name="tripId">
         <input type="submit" value="submit">
        </form>
        
        <!-- 
         <form class="transferallform" action="transfer/all.do" method="post" >
         <input type="submit" value="Transfer All Trip" >
        </form>
         -->
        
    </jsp:body>
</lesson:page>