// Animations: a still image by default, the animated version plays on hover
// (or when visible / on tap on touch screens).
(function () {
  "use strict";

  const figures = document.querySelectorAll(".anim[data-anim]");
  const touch = window.matchMedia("(hover: none)").matches;
  const calm = window.matchMedia("(prefers-reduced-motion: reduce)").matches;

  function play(fig) {
    const img = fig.querySelector("img");
    img.src = fig.dataset.anim + "#" + Date.now(); // new url = restart from the first frame, still cached
    fig.classList.add("playing");
  }

  function stop(fig) {
    const img = fig.querySelector("img");
    img.src = fig.dataset.still;
    fig.classList.remove("playing");
  }

  // load the animations once they are close to the screen
  const preload = new IntersectionObserver((entries) => {
    entries.forEach((e) => {
      if (!e.isIntersecting) return;
      new Image().src = e.target.dataset.anim;
      preload.unobserve(e.target);
    });
  }, { rootMargin: "300px" });

  figures.forEach((fig) => {
    fig.dataset.still = fig.querySelector("img").getAttribute("src");
    preload.observe(fig);

    if (!touch) {
      fig.addEventListener("mouseenter", () => play(fig));
      fig.addEventListener("mouseleave", () => stop(fig));
    } else {
      fig.addEventListener("click", () => (fig.classList.contains("playing") ? stop(fig) : play(fig)));
    }
  });

  // on phones, play what is in the middle of the screen
  if (touch && !calm) {
    const visible = new IntersectionObserver((entries) => {
      entries.forEach((e) => (e.isIntersecting ? play(e.target) : stop(e.target)));
    }, { threshold: 0.7 });
    figures.forEach((fig) => visible.observe(fig));
  }
})();
