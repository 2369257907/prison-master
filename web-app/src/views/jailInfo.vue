<template>
  <baidu-map class="map" :center="{lng: 116.50, lat: 39.91}" :zoom="15" :continuous-zoom="true" :scroll-wheel-zoom="true">
    <bm-marker :position="{lng: 116.50, lat: 39.91}"   @click="infoWindowOpen">
      <bm-info-window :position="{lng: 116.50, lat: 39.91}" title="监狱信息" :show="infoWindow.show" @close="infoWindowClose" @open="infoWindowOpen">
        <p v-text="infoWindow.name"></p>
        <p v-text="infoWindow.leader"></p>
        <p v-text="infoWindow.openTime"></p>

      </bm-info-window>
    </bm-marker>
  </baidu-map>
</template>
<script>
export default {
  name: "jailInfo",
  data(){
    return {
      infoWindow: {
        show: true,
        name: "名称：北京市监狱管理局",
        leader: "局长：王富贵",
        openTime: "监狱创建时间：2022-4-10"
      }
    }
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