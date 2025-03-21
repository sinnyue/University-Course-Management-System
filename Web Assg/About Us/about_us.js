document.addEventListener("DOMContentLoaded", function() {
    const parentContainers = document.querySelectorAll(".about-content");
  
    parentContainers.forEach(function(parentContainer) {
      parentContainer.addEventListener("click", function(event) {
        const current = event.target;
  
        const isReadMore = current.classList.contains("read-more");
  
        if (!isReadMore) {
          return;
        }
  
        const currentText = current.parentNode.querySelector(".read-more-text");
  
        currentText.classList.toggle("read-more-text--show");
  
        current.textContent = currentText.classList.contains("read-more-text--show")
          ? "Read Less..."
          : "Read More...";
      });
    });
  });