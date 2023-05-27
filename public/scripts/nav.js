let nav = document.querySelector("nav");
    window.onscroll = function () {
      if (document.documentElement.scrollTop > 20) {
        nav.classList.add("sticky");
      } else {
        nav.classList.remove("sticky");
      }
    }