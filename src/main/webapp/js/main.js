/**
 * Created by lch on 2017/9/26.
 */


var scale=[];
scale[0]=5.364418029785158E-6*256;
scale[1]=scale[0]/2;
scale[2]=scale[1]/2;
scale[3]=scale[2]/2;


var tdtvname = "天地图矢量图";
var tdtiname = "天地图卫星图";
var tdttname = "天地图地形图";
var tdtaname = "天地图标注层";
var tdtvlayer = "http://t{0-7}.tianditu.com/DataServer?T=vec_c&x={x}&y={y}&l={z}";
var tdtilayer = "http://t{0-7}.tianditu.com/DataServer?T=img_c&x={x}&y={y}&l={z}";
var tdttlayer = "http://t{0-7}.tianditu.com/DataServer?T=ter_c&x={x}&y={y}&l={z}";
var tdtalayer = "http://t3.tianditu.com/DataServer?T=cva_c&x={x}&y={y}&l={z}";
var vurl = "http://t{m}.tianditu.com/DataServer?T=vec_c&X={x}&Y={y}&L={z}"
var szvurl = "http://ag.szgeoinfo.com/arcgis/rest/services/szmap/MapServer/tile/{z}/{y}/{x}";
var iurl = "http://t0.tianditu.com/DataServer?T=img_c&X={x}&Y={y}&L={z}"
var sziurl = "http://ag.szgeoinfo.com/arcgis/rest/services/szimage/MapServer/tile/{z}/{y}/{x}";
var turl = "http://t0.tianditu.com/DataServer?T=ter_c&X={x}&Y={y}&L={z}"

var vannurl = "http://t{m}.tianditu.com/DataServer?T=cva_c&X={x}&Y={y}&L={z}"
var szvannurl = "http://ag.szgeoinfo.com/arcgis/rest/services/szmap_ann/MapServer/tile/{z}/{y}/{x}";
var iannurl = "http://t0.tianditu.com/DataServer?T=cia_c&X={x}&Y={y}&L={z}"
var sziannurl = "http://ag.szgeoinfo.com/arcgis/rest/services/szimage_ann/MapServer/tile/{z}/{y}/{x}";
var tannurl = "http://t0.tianditu.com/DataServer?T=cta_c&X={x}&Y={y}&L={z}"


var projectionExtent=null;
var resolutions;

var szminlon=113.74;
var szminlat=22.38;
var szmaxlon=114.63;
var szmaxlat=22.86;

var minrow=[];
var mincol=[];
var maxcol=[];
var maxrow=[];
var szzoom=18;

var projection = ol.proj.get("EPSG:4326");
var projectionExtent = [ -180, -90, 180, 90 ];
var maxResolution = (ol.extent.getWidth(projectionExtent) / (256 * 2));
var resolutions = new Array(22);
var z;
for (z = 0; z < 22; ++z) {
    resolutions[z] = maxResolution / Math.pow(2, z);
    //  console.log(z+" "+resolutions[z]);
}
var tileOrigin = ol.extent.getTopLeft(projectionExtent);

//天地图矢量图
var TianDiTuVLayer = function() {
    return new ol.layer.Tile({
        title:tdtvname,
        extent: [ -180, -90, 180, 90 ],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }

                if(z<szzoom){
                    return vurl.replace('{m}', Math.floor(Math.random()*7))
                        .replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }else{
                    return szvurl.replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }
            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
};

//天地图卫星图
var TianDiTuILayer = function() {
    return new ol.layer.Tile({
        title:tdtiname,
        visible:false,
        extent: [ -180, -90, 180, 90 ],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }

                if(z<szzoom){
                    return iurl.replace('{m}', Math.floor(Math.random()*7))
                        .replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }else{
                    return sziurl.replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }
            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
};



//天地图地形图
var TianDiTuTLayer = function() {
    return new ol.layer.Tile({
        title:tdttname,
        visible:false,
        extent: [ -180, -90, 180, 90 ],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }


                return turl.replace('{m}', Math.floor(Math.random()*7))
                    .replace('{z}', z.toString())
                    .replace('{y}', y.toString())
                    .replace('{x}', x.toString());

            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
};

//天地图标注图
var TianDiTuAnnVLayer = function() {

    return new ol.layer.Tile({

        extent: [ -180, -90, 180, 90 ],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }

                if(z<szzoom){
                    return vannurl.replace('{m}', Math.floor(Math.random()*7))
                        .replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }else{
                    return szvannurl.replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }
            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
};


var TianDiTuAnnILayer = function() {

    return new ol.layer.Tile({

        extent: [ -180, -90, 180, 90 ],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }

                if(z<szzoom){
                    return iannurl.replace('{m}', Math.floor(Math.random()*7))
                        .replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }else{
                    return sziannurl.replace('{z}', z.toString())
                        .replace('{y}', y.toString())
                        .replace('{x}', x.toString());
                }
            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
};


var TianDiTuAnnTLayer = function() {

    return new ol.layer.Tile({

        extent: [ -180, -90, 180, 90 ],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }

                return tannurl.replace('{m}', Math.floor(Math.random()*7))
                    .replace('{z}', z.toString())
                    .replace('{y}', y.toString())
                    .replace('{x}', x.toString());

            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
};



