var modal = document.getElementById("viewImageModal");
var modalImg = document.getElementById("modalImage");
var captionText = document.getElementById("caption");
document.querySelectorAll(".view-image-btn").forEach((button) => {
	button.onclick = function () {
		modalImg.classList.add("fade-in");
		modal.style.opacity = 1;
		setTimeout(() => {
			modal.classList.remove("hidden");
			modal.classList.add("flex");
		}, 300);
		modalImg.src = this.getAttribute("data-image-src");
		captionText.innerHTML = this.getAttribute("data-image-alt");
	};
});

document.querySelector(".close").onclick = function () {
	modalImg.classList.add("fade-out");
	setTimeout(() => {
		modalImg.classList.remove("fade-out");
	}, 250);

	setTimeout(() => {
		modal.style.opacity = 0;
		setTimeout(() => {
			modal.classList.add("hidden");
		}, 200);
	}, 250);
};
