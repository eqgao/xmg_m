<style type="text/css">
    .fire{display:block; overflow:hidden; font-size:12px; position:absolute};
    body{overflow:hidden; background:#000}
    html{overflow:hidden; background:#000}
</style>
<script type="text/javascript">
    var Fire = function(r, color) {
        this.radius = r || 12;
        this.color = color || "FF6600";
        this.xpos = 0;
        this.ypos = 0;
        this.zpos = 0;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
        this.mass = 1;
        this.p = document.createElement("span");
        this.p.className = "fire";
        this.p.innerHTML = "*";
        this.p.style.fontSize = this.radius + "px";
        this.p.style.color = "#" + this.color;
    }
    Fire.prototype = {
        append: function(parent) {
            parent.appendChild(this.p);
        },
        setSize: function(scale) {
            this.p.style.fontSize = this.radius * scale + "px";
        },
        setPosition:function(x, y) {
            this.p.style.left = x + "px";
            this.p.style.top =  y + "px";
        },
        setVisible: function(b) {
            this.p.style.display = b ? "block" : "none";
        }
    }
    var fireworks = function() {
        var fires = new Array();
        var count = 100;
        var fl = 250;
        var vpx = 500;
        var vpy = 300;
        var gravity = .3;
        var floor = 200;
        var bounce = -.8;
        var timer;
        return {
            init: function() {
                for (var i=0; i<count; i++) {
                    var color = 0xFF0000;
                    color = (Math.random() * 0xFFFFFF).toString(16).toString().split(".")[0];
                    while(color.length < 6) {
                        color = "0" + color;
                    }
                    var fire = new Fire(12, color);
                    fires.push(fire);
                    fire.ypos = -100;
                    fire.vx = Math.random() * 6 - 3;
                    fire.vy = Math.random() * 6 - 3;
                    fire.vz = Math.random() * 6 - 3;
                    fire.append(document.body);
                }
                var that = this;
                timer = setInterval(function() {
                    for (var i=0;i<count; i++) {
                        that.move(fires[i]);
                    }
                }, 30);
            },
            move: function(fire) {
                fire.vy += gravity;
                fire.xpos += fire.vx;
                fire.ypos += fire.vy;
                fire.zpos += fire.vz;
                if (fire.ypos > floor) {
                    fire.ypos = floor;
                    fire.vy *= bounce;
                }
                if (fire.zpos > -fl) {
                    var scale = fl/ (fl+fire.zpos);
                    fire.setSize(scale);
                    fire.setPosition(vpx + fire.xpos * scale,
                            vpy + fire.ypos * scale);
                    fire.setVisible(true);
                } else {
                    fire.setVisible(false);
                }
            }
        }
    }
    fireworks().init();
</script>
