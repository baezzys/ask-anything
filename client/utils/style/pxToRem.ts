const pxToRem = (pixel: number): string => {
  const basicFontSize = 16;

  return `${pixel / basicFontSize}rem`;
};

export default pxToRem;
