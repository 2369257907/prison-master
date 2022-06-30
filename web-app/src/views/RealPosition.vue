<template>
  <div>
    <p style="text-align: center; color: #03549a;font-size: 30px">犯人实时定位</p>
  <baidu-map class="map" :center="{lng:116.50, lat:  39.91}" :zoom="15" :continuous-zoom="true" :scroll-wheel-zoom="true">
<!--    在监狱半径范围内画一个圆圈-->
    <bm-circle :center="circlePath.center" :radius="circlePath.radius" stroke-color="blue" :stroke-opacity="0.5" :stroke-weight="2"  ></bm-circle>
<!--    标记监狱位置点-->
    <bm-marker :position="{lng: 116.50, lat: 39.91}"   @click="infoWindowOpen">
      <bm-info-window :position="{lng: 116.50, lat: 39.91}" title="监狱信息" :show="infoWindow.show" @close="infoWindowClose" @open="infoWindowOpen">
        <p v-text="infoWindow.name"></p>
        <p v-text="infoWindow.leader"></p>
        <p v-text="infoWindow.openTime"></p>
      </bm-info-window>
    </bm-marker>


<!--    标记犯人位置点-->
    <bm-marker :position="{lng: this.prisonerLng, lat: this.prisonerLat}"  :icon="{url: 'http://developer.baidu.com/map/jsdemo/img/fox.gif', size: {width: 300, height: 157}}" >
    </bm-marker>
  </baidu-map>
  </div>
</template>
<script>
export default {
  name: "RealPosition",
  data(){
    return {
      prisonerLng: "",
      prisonerLat: "",
      infoWindow: {
        show: false,
        name: "名称：北京市监狱管理局",
        leader: "局长：王富贵",
        openTime: "监狱创建时间：2022-4-10"
      },
      circlePath: {
        center: {
          lng: 116.50,
          lat: 39.91
        },
        radius: 1200
      }
    }
  },
  mounted() {
    this.getRealPositionWithApiAndAlert()
    // this.getDistanceAndAlert(39.915, 116.504, this.prisonerLat,this.prisonerLng)
    this.alert(this.getDistanceAndAlert(39.91, 116.50, this.prisonerLat,this.prisonerLng))

  },
  methods: {
    infoWindowClose (e) {
      this.infoWindow.show = false
    },
    infoWindowOpen (e) {
      this.infoWindow.show = true
    },
    clear () {
      this.infoWindow.contents = ''
    },
    getRealPositionWithApiAndAlert(){
      var jailLng = 116.504;
      var jailLat = 39.915;
      //此处为模拟获取犯人实时定位，实际需要调取api获取电子手铐的实时定位
      this.prisonerLng = jailLng + Math.random()*0.01;
      this.prisonerLat = jailLat + Math.random()*0.01;
    },
    getDistanceAndAlert(lat1,lng1,lat2,lng2){
      let EARTH_RADIUS = 6378.137;
      let radLat1 = this.rad(lat1);
      let radLat2 = this.rad(lat2);
      let a = radLat1 - radLat2;
      let b = this.rad(lng1) - this.rad(lng2);
      let s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
          + Math.cos(radLat1) * Math.cos(radLat2)
          * Math.pow(Math.sin(b / 2), 2)));
      s = s * EARTH_RADIUS;
      //s = Math.round(s * 10000d) / 10000d;
      s = Math.round(s * 10000) / 10000;
      s = s*1000;    //乘以1000是换算成米
      console.log(s)
      return s;

    },
  rad(d){
    return d * Math.PI / 180.0;
  },
    alert(distance){
      if (distance>this.circlePath.radius){
        this.$message({
          message: '警告!当前犯人距离监狱'+distance+"m",
          type: 'error'
        });
      }else {
        this.$message({
          message: "当前犯人在监狱中",
          type: 'success'
        });
      }
    }
  }
}
</script>

<style scoped>
.map {
  width: 100%;
  height: 500px;
}
</style>