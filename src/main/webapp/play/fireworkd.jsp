<script>
    var lightWave = function (T, left, thick, sharp, speed, vibration, amplitude, opacity) {
        this.cont = T;//炫光容器
        this.left = left;//炫光向右偏移量
        this.thick = thick;//粗细
        this.sharp = sharp;//尖锐度
        this.speed = speed;//波动速度
        this.vibration = vibration;//单位时间内的振动频率
        this.amplitude = amplitude;//振幅
        this.opacity = opacity;//透明度
        this.cont.style.position = 'relative';
        this.move();
    }
    lightWave.prototype = {
        point: function (n, l, t, c, color) {
            var p = document.createElement('p');
            p.innerHTML = '&nbsp;';
            p.style.top = t + 'px';
            p.style.left = l + 'px';
            p.style.width = 1 + 'px';
            p.style.height = n + 'px';
            p.style.filter = 'alpha(opacity=' + this.opacity + ')';
            p.style.lineHeight = 0;
            p.style.position = 'absolute';
            p.style.background = color;
            c.appendChild(p);
            return this;
        },
        color: function () {
            var c = ['0', '3', '6', '9', 'c', 'f'];
            var t = [c[Math.floor(Math.random() * 100) % 6], '0', 'f'];
            t.sort(function () {
                return Math.random() > 0.5 ? -1 : 1;
            });
            return '#' + t.join('');
        },
        wave: function () {
            var l = this.left, t = this.wavelength, color = this.color();
            var c = document.createElement('div');
            c.style.top = this.amplitude + 20 + 'px';
            c.style.position = 'absolute';
            c.style.opacity = this.opacity / 100;
            for (var i = 1; i < this.thick; i++) {
                for (var j = 0; j < this.thick * this.sharp - i * i; j++, l++) {
                    this.point(i, l, -9999, c, color);
                }
            }
            for (var i = this.thick; i > 0; i--) {
                for (var j = this.thick * this.sharp - i * i; j > 0; j--, l++) {
                    this.point(i, l, -9999, c, color);
                }
            }
            this.cont.appendChild(c);
            return c;
        },
        move: function () {
            var wl = this.amplitude;
            var vibration = this.vibration;
            var w = this.wave().getElementsByTagName('p');
            for (var i = 0; i < w.length; i++) {
                w[i].i = i;
            }
            var m = function () {
                for (var i = 0, len = w.length; i < len; i++) {
                    if (w[i].ori == true) {
                        w[i].i -= vibration;
                        var top = w[i].i % 180 == 90 ? 0 : wl * Math.cos(w[i].i * Math.PI / 180);
                        w[i].style.top = top + 'px';
                        if (parseFloat(w[i].style.top) <= -wl) {
                            w[i].ori = false;
                        }
                    } else {
                        w[i].i += vibration;
                        var top = w[i].i % 180 == 90 ? 0 : wl * Math.cos(w[i].i * Math.PI / 180);
                        w[i].style.top = top + 'px';
                        if (parseFloat(w[i].style.top) >= wl) {
                            w[i].ori = true;
                        }
                    }
                }
            }
            setInterval(m, this.speed);
        }
    }
    window.onload = function () {
        var targetDom = document.body;
        new lightWave(targetDom, 0, 3, 36, 120, 6, 20, 40);
        new lightWave(targetDom, 50, 2, 70, 120, 10, 30, 30);
    }
</script>
