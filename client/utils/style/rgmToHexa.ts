const colorToHex = (color: number) => {
  const hexadecimal = color.toString(16);
  return hexadecimal.length === 1 ? `0${hexadecimal}` : hexadecimal;
};

type rgbToHexProps = {
  red: number;
  green: number;
  blue: number;
};

const rgbToHex = ({ red, green, blue }: rgbToHexProps) =>
  `#${colorToHex(red)}${colorToHex(green)}${colorToHex(blue)}`;

export default rgbToHex;
