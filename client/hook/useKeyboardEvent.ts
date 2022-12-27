import { useEffect } from "react";

export default function useKeyboardEvent(
  keyCode: string,
  callback: (e: KeyboardEvent) => void
) {
  const keyupCallback = (e: KeyboardEvent) => {
    if (e.code !== keyCode) return;
    callback(e);
  };

  useEffect(() => {
    document.addEventListener("keyup", keyupCallback);

    return () => {
      document.removeEventListener("keyup", keyupCallback);
    };
  }, []);
}
