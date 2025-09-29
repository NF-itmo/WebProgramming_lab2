const convertCheckerCordsToSvgCords = (
    X, Y, R,
    paddingX = 20,
    paddingY = 20,
    XAxisSize = 160,
    YAxisSize = 160
) => {
    return [paddingX + (1 + X/R)*XAxisSize/2, paddingY + (1 - Y/R)*YAxisSize/2];
}

const convertSvgCordsToCheckerCords = (
    absX, absY,
    centerX, centerY,
    paddingX, paddingY,
    scale,
    R
) => {
    return [
        (((absX - paddingX) - centerX) / scale)*R,
        ((centerY - (absY - paddingY)) / scale)*R,
        R
    ];
};

const findNearest = (target, elemsArray) => {
    let closest = null;
    let minDiff = Infinity;
    
    elemsArray.forEach(elem => {
        const value = parseFloat(elem.value);
        const diff = Math.abs(target - value);
        if (diff < minDiff) {
            minDiff = diff;
            closest = elem;
        }
    });

    return closest;
}

// Кликоуловтель 2.1
const handlePlotClick = (event) => {
    return new Promise((resolve, reject) => {
        const R = document.getElementById("graph-test-form")["R"].value;

        if (!R) reject("Сначала выберите радиус R");

        const plot = document.getElementById("graph-svg");
        const rect = plot.getBoundingClientRect();

        const [X,Y,] = convertSvgCordsToCheckerCords(
            event.clientX, event.clientY,
            rect.width/2, rect.height/2,
            rect.left, rect.top,
            80,
            R
        )

        resolve(
            [
                X,
                findNearest(
                    Y,
                    document.querySelectorAll('input[name="Y"]')
                ).value,
                R
            ]
        )
    })
};