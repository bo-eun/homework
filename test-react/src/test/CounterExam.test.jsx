import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import CounterExam from "../exam/CounterExam";
import userEvent from "@testing-library/user-event";


const user = userEvent.setup();

describe('카운터 테스트', () => {
    it('첫 렌더링 시', () => {
        render(<CounterExam />);

        expect(screen.getByText('결과 : 0')).toBeInTheDocument();
    })

    it('증가하기 버튼 클릭 시 ', async () => {
        render(<CounterExam />);
        const button = screen.getByRole('button', { name: '증가하기' });

        await user.click(button);
        expect(screen.getByText('결과 : 1')).toBeInTheDocument();
    })

    it('감소하기 버튼 클릭 시 ', async () => {
        render(<CounterExam />);
        const button = screen.getByRole('button', { name: '감소하기' });

        await user.click(button);
        expect(screen.getByText('결과 : -1')).toBeInTheDocument();
    })
});