import { useEffect } from "react";

export default function useClickEvent(callback: (e: MouseEvent) => void) {
  useEffect(() => {
    document.addEventListener("click", callback);

    return () => {
      document.removeEventListener("click", callback);
    };
  }, []);
}
